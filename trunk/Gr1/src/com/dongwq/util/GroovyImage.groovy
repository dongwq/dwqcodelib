package com.dongwq.util

/**
* A batch image manipulation utility
*
* A wrote this script just to get groovy, batch manipulate images in about
* 240 lines of code (without this comment)!!!.
*
* commands:
* values ending with '%' means size relative to image size.
* values ending with 'px' means values in absolute pixels.
* values without postfix use default notation.
*
* expressions:
* scale(width,height)      * height is optional(use width) e.g: scale(50%) == scale(50%,50%)
* fit(width,height)        * relative scale the image until if fits (defaut as scale)
*                          * bounds of the given box, usefull for generating of thumbnails.
* rotate(degrees,x,y)      * the rotation position x and y are optional (default is 50%)
*
* TODO: move(x,y)          * move the image within its own bounds (can be done with margin)
*                          * y is optional(same height)
* TODO: color(type)        * color transformation
* TODO: shear(degrees,x,y) * x and y is optional
* margin(x,y,x2,y2)        * add margins to image (resize image canvas), this operation can't
*                          * be used on a headless environment.
* parameters:
* -d                       * working directory (default current directory)
* -e                       * execute expressions from command line.
* -f                       * execute expressions from file.
* -p                       * file mathing pattern default is \.png|\.jpg
* -q                       * output file pattern can use {0} .. {9}
*                          * backreferences from the input pattern. default: output/{0}
* -h                       * help, nothing special (maybe this doc using heredoc)
*
* Example generate thumbnails(take *.png from images fit them in a 100X100 box,
* add 10px margin, put them in the thumbnail dir.)
*
* $ groovy image.groovy -d images -e "fit(100px,100px) margin(5)" -p "(.*)\.png" -q "thumbnail/{1}.png"
*
* @author Philip Van Bogaert alias tbone
*/

import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.util.*;


class GroovyImage {
   File srcDir = new File(".")
   def operations = []
   def pattern = ~".*(\\.png|\\.jpg)"
   def outputPattern = "output/{0}"

   void addOperation(command) {

	   def matcher = command =~ "([a-z]+)\\((.*)\\).*";
	   matcher.find();

	   def method = matcher.group(1);
	   def args = matcher.group(2).split(",").toList();

	   switch(method) {
		   case "scale": // vertical,horizontal
			   operations.add([this.&parseAndScale,argsLength(args,2)]);
			   break;

		   case "rotate": // degrees,x,y
			   operations.add([this.&parseAndRotate,argsLength(args,3)]);
			   break;

		   case "margin": // left,top,right,bottom
			   operations.add([this.&parseAndMargin,argsLength(args,4)]);
			   break;

		   case "fit": // width,height
			   operations.add([this.&parseAndFit,argsLength(args,2)]);
			   break;
	   }
   }

   BufferedImage parseAndRotate(image,degrees,x,y) {
	   def parsedRadians = 0;
	   try {
		   parsedRadians = Math.toRadians(Double.parseDouble(degrees));
	   }
	   catch(NumberFormatException except) {
	   }

	   def parsedX = parseValue(x,image.width,true,"50%");
	   def parsedY = parseValue(y,image.height,true,parsedX);

	   return rotate(image,parsedRadians,parsedX,parsedY);
   }

   BufferedImage rotate(image,radians,x,y) {
	   def transform = new AffineTransform();
	   transform.rotate(radians,x,y);
	   def op = new AffineTransformOp(transform,AffineTransformOp.TYPE_BILINEAR);
	   return op.filter(image,null);
   }

   BufferedImage parseAndScale(image,horizontal,vertical) {
	   def parsedHorizontal =  parseValue(horizontal,image.width,false,"100%");
	   def parsedVertical =  parseValue(vertical,image.height,false,parsedHorizontal);
	   return scale(image,parsedHorizontal,parsedVertical);
   }

   BufferedImage scale(image,horizontal,vertical) {
	   def transform = new AffineTransform();
	   transform.scale(horizontal,vertical);
	   def op = new AffineTransformOp(transform,AffineTransformOp.TYPE_BILINEAR);
	   return op.filter(image,null);
   }

   BufferedImage parseAndMargin(image,left,top,right,bottom) {
	   def parsedLeft = parseValue(left,image.width,true,"0px");
	   def parsedTop =  parseValue(top,image.height,true,parsedLeft);
	   def parsedRight = parseValue(right,image.width,true,parsedLeft);
	   def parsedBottom = parseValue(bottom,image.height,true,parsedTop);
	   return margin(image,parsedLeft,parsedTop,parsedRight,parsedBottom);
   }

   BufferedImage margin(image,left,top,right,bottom) {
	   def width = left + image.width + right;
	   def height = top + image.height + bottom;
	   def newImage = new BufferedImage(width.intValue(), height.intValue(),BufferedImage.TYPE_INT_ARGB);
	   // createGraphics() needs a display, find workaround.
	   def graph = newImage.createGraphics();
	   graph.drawImage(image,new AffineTransform(1.0d,0.0d,0.0d,1.0d,left,top),null);
	   return newImage;
   }

   BufferedImage parseAndFit(image,width,height) {
	   def parsedWidth = parseValue(width,image.width,true,"100%");
	   def parsedHeight = parseValue(height,image.height,true,parsedWidth);

	   def imageRatio = image.width / image.height;
	   def fitRatio = parsedWidth / parsedHeight;

	   if(fitRatio < imageRatio) {
		   parsedHeight = image.height * (parsedWidth/image.width);
	   } else {
		   parsedWidth = image.width * (parsedHeight/image.height);
	   }

	   return parseAndScale(image,parsedWidth+"px",parsedHeight+"px");
   }

   BufferedImage manipulate(image) {
	   for(operation in operations) {
		   image = operation[0].call([image] + operation[1]);
	   }
	   return image;
   }

   void batch() {
	   def images = getImages();
	   for(imageMap in images) {
		   imageMap.image = manipulate(imageMap.image);
		   storeImage(imageMap);
	   }
   }


	Object getImages() {
	   def imageMaps = [];

	   for(i in srcDir.listFiles()) {
		   if(!i.isDirectory()) {
			   def subpath = i.path;
			   if(subpath.startsWith(srcDir.path)) {
				   subpath = subpath.substring(srcDir.path.length());
			   }
			   def matcher = subpath =~ pattern;
			   if(matcher.find()) {
				   imageMaps.add(["file":i,"matcher":matcher]);
			   }
		   }
	   }
	   imageMaps.each({it["image"] = ImageIO.read(it["file"]); });
	   return imageMaps;
   }


   void storeImage(imageMap) {
	   def groupIndex = 0;
	   def name = outputPattern;
	   def matcher = imageMap.matcher;
	   while(groupIndex <= matcher.groupCount()) {
		   name = name.replaceAll("\\{${groupIndex}\\}",matcher.group(groupIndex++));
	   }
	   def type = name.substring(name.lastIndexOf(".")+1,name.length());
	   def file = new File(srcDir,name);
	   file.mkdirs();
	   ImageIO.write(imageMap.image,type,file);
   }

   static void main(args) {
	   def argList = args.toList();
	   def script ='';
	   def groovyImage = new GroovyImage();

	   // command line parsing bit, NOTE: -h does System.exit(2)
	   def argAndClosure = ['-d':{groovyImage.srcDir = new File(it)},
						'-q':{groovyImage.outputPattern = it},
						'-p':{groovyImage.pattern = it},
						'-h':{groovyImage.help()}];
	   // parse non-conditional arguments
	   parseMultipleCommandArgs(argList,argAndClosure);

	   // expression,file,nothing
	   if(!parseCommandArg(argList,'-e', {script = it}))  {
		   parseCommandArg(argList,'-f',{script = new File(it).text});
	   }

	   // execution bit
	   def commands = script =~ "([a-z]{1,}\\([^)]*\\))";
	   while(commands.find()) {
		   groovyImage.addOperation(commands.group(1));
	   }
	   groovyImage.batch();

   }

   static boolean parseCommandArg(args,arg,closure) {
	   def index = args.indexOf(arg);

	   if(index != -1 && index + 1 < args.size()) {
		   closure.call(args[index + 1]);
		   return true;
	   } else {
		   return false;
	   }
   }

   static void parseMultipleCommandArgs(args,argAndClosureMap) {
	   for(argAndClosure in argAndClosureMap) {
		   parseCommandArg(args,argAndClosure.key,argAndClosure.value);
	   }
   }

   void help() {
	   println('usage: groovy image.groovy -i <inputDir> -o <outputDir> -e "<expressions>"');
	   System.exit(2);
   }

   /**
	* absolute true  -> returns pixels.
	*          false -> returns relative decimal (e.g 1.0).
	*/
   Number parseValue(value,size,absolute,defaultValue="0") {
	   def pattern = "(-?[0-9]+\\.?[0-9]*)(.*)";
	   def matcher = value =~ pattern;
	   if(!matcher.find()) {
		   matcher = defaultValue =~ pattern;
		   matcher.find();
	   }

	   def decimalValue = Double.parseDouble(matcher.group(1));
	   def type = matcher.group(2);

	   if(absolute) { // pixels
		   switch(type)  {
			   case "%":
				   return (int) size * (decimalValue / 100);
			   case "px":
			   default:
			   return (int) decimalValue;
		   }
	   }
	   else { // scale
		   switch(type) {
			   case "px":
				   return decimalValue / size;
			   case "%":
				   return decimalValue / 100;
			   default:
				   return decimalValue;
		   }
	   }
   }

   Object argsLength(args,length) {
	   if(args.size() < length) {
		   while(args.size() < length) {
			   args.add("");
		   }
	   } else {
		   args = args.subList(0,length);
	   }
	   return args;
   }
}

