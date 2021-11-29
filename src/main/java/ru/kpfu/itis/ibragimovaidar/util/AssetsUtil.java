package ru.kpfu.itis.ibragimovaidar.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class AssetsUtil {

	public static Path getPath(String stringPath){
		try {
			return Paths.get(AssetsUtil.class.getResource(stringPath).toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	public static InputStream getInputStream(String stringPath){
		try {
			return Files.newInputStream(getPath(stringPath));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
