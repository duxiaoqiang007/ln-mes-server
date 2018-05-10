/*
 * 
 * Copyright 2014 Jules White
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.smu.vaan.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


/**
 * This class provides a simple implementation to store video binary
 * data on the file system in a "videos" folder. The class provides
 * methods for saving videos and retrieving their binary data.
 *
 * @author jules
 *
 */
public class FileManager {

	private static final String UPLOAD_FOLDER = "/upload";
	private static final String DEFAULT_FOLDER = "/WEB-INF/classes/static" + UPLOAD_FOLDER;
//	private static final String DEFAULT_LOCAL_FOLDER = "/Users/vaan/upload/";

	private Path targetDir_;


	public FileManager(HttpServletRequest request) throws IOException{
		this(request, DEFAULT_FOLDER);
	}

	// The VideoFileManager.get() method should be used
	// to obtain an instance
	public FileManager(HttpServletRequest request, String dir) throws IOException{
		String filePathName = request.getSession().getServletContext().getRealPath(dir);
		targetDir_ = Paths.get(filePathName);

		if(!Files.exists(targetDir_)){
			Files.createDirectories(targetDir_);
		}
	}

	public FileManager(String localPath) throws IOException {
		targetDir_ = Paths.get(localPath);
		if(!Files.exists(targetDir_)){
			Files.createDirectories(targetDir_);
		}
	}

    public static FileManager get(HttpServletRequest request, String localPath) throws IOException {
		if (null != localPath) {
			return new FileManager(localPath);
		}
        return new FileManager(request);
    }

	// Private helper method for resolving video file paths
	private Path getPath(String fileName){
		return targetDir_.resolve(fileName);
	}

	/**
	 * This method returns true if the specified Video has binary
	 * data stored on the file system.
	 *
	 * @param v
	 * @return
	 */
//	public boolean hasVideoData(Video v){
//		Path source = getVideoPath(v);
//		return Files.exists(source);
//	}

	/**
	 * This method copies the binary data for the given video to
	 * the provided output stream. The caller is responsible for
	 * ensuring that the specified Video has binary data associated
	 * with it. If not, this method will throw a FileNotFoundException.
	 *
	 * @param v
	 * @param out
	 * @throws IOException
	 */
//	public void copyVideoData(Video v, OutputStream out) throws IOException {
//		Path source = getVideoPath(v);
//		if(!Files.exists(source)){
//			throw new FileNotFoundException("Unable to find the referenced video file for videoId:"+v.getId());
//		}
//		Files.copy(source, out);
//	}

	/**
	 * This method reads all of the data in the provided InputStream and stores
	 * it on the file system. The data is associated with the Video object that
	 * is provided by the caller.
	 *
	 * @param fileName 文件名
	 * @param data 文件数据
	 * @throws IOException
	 */
	public String saveData(String fileName, InputStream data) throws IOException{
		assert(data != null);

		Path target = getPath(fileName);
		Files.copy(data, target, StandardCopyOption.REPLACE_EXISTING);
		return target.getFileName().toString();
	}



}
