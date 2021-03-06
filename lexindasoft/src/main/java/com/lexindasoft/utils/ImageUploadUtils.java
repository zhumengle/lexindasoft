package com.lexindasoft.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.zip.Inflater;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import com.lexindasoftservice.utils.EasyImage;

@SuppressWarnings("restriction")
public class ImageUploadUtils {
	
	private static final Log log = LogFactory.getLog(ImageUploadUtils.class);
	
	public static String uploadImg(MultipartFile multipartFile, String rootDir, String filePrefix, int limitWidth) {
		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename().toLowerCase());
		InputStream in = null;
		try {
			String path = getFilePath(rootDir, filePrefix, extension);
			in = multipartFile.getInputStream();
			Image src = ImageIO.read(multipartFile.getInputStream());
			BufferedImage sourceImg = ImageIO.read(multipartFile.getInputStream());
	        System.out.println(sourceImg.getWidth());  
	        System.out.println(sourceImg.getHeight());  
			if(limitWidth > 0)
				EasyImage.resize(src, path, limitWidth, 0);
			else
				EasyImage.save(src, path);
//				EasyImage.composePic(src,path);
			log.info("upload image successfully,path:" + path);
			return path;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("upload image fail, file:" + multipartFile.getOriginalFilename());
			return null;
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
	
	public static String resizeImg(MultipartFile multipartFile, String rootDir, String filePrefix, int limitWidth,int limitHeight) {
		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename().toLowerCase());
		InputStream in = null;
		try {
			String path = getFileResizePath(rootDir, filePrefix, extension);
			in = multipartFile.getInputStream();
			
			Image src = ImageIO.read(multipartFile.getInputStream());
			BufferedImage sourceImg = ImageIO.read(multipartFile.getInputStream());
	        System.out.println(sourceImg.getWidth());  
	        System.out.println(sourceImg.getHeight());  
			EasyImage.resize(src, path, limitWidth, limitHeight);
			log.info("upload image successfully,path:" + path);
			return path;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("upload image fail, file:" + multipartFile.getOriginalFilename());
			return null;
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
	
	public static String getFilePath(String rootDir, String filePrefix, String extension){
		String dirPath = rootDir;
		String fileName = filePrefix + ".jpg";
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return rootDir+File.separator+ fileName;
	}
	
	public static String getFileResizePath(String rootDir, String filePrefix, String extension){
		String dirPath = rootDir;
		String fileName = filePrefix+ "." + extension;
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		return rootDir+File.separator+ fileName;
	}
	
	/**
	 * 是否有效的图片格式
	 * @param file
	 * @return
	 */
	public static boolean isValidFormats(MultipartFile file) {
		if (file != null) {
			String filename = file.getOriginalFilename().toLowerCase();
			if (!filename.endsWith(".jpg") && !filename.endsWith(".jpeg")
					&& !filename.endsWith(".png") && !filename.endsWith(".gif")) {
				return true;
			}
		}
		return false;
	}
	
	public static byte[] decompressBytes(byte input[]) { 
		byte output[] = new byte[0]; 
		Inflater decompresser = new Inflater(); 
		decompresser.setInput(input); 
		ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length); 
		try { 
			byte[] buf = new byte[1024];
			int got; 
			while (!decompresser.finished()) { 
				got = decompresser.inflate(buf); 
				bos.write(buf, 0, got); 
			} 
			output = bos.toByteArray(); 
		}
		catch(Exception e) { 
			log.error("decompress failed with exception:" + e.getMessage());
		}
		finally { 
			try { 
				bos.close(); 
			}
			catch (IOException e) { 
				e.printStackTrace(); 
			} 
		} 
		
		return output; 
	} 
}
