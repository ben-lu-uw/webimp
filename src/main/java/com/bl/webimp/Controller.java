package com.bl.webimp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@SpringBootApplication
@RestController
public class Controller {

	public static void main(String[] args) {
		SpringApplication.run(Controller.class, args);
	}

	private final Path rootLocation = Paths.get("C:\\Users\\avtea\\Desktop\\repo\\webimp\\src\\main\\resources\\static\\imgInput");

	public void store(MultipartFile file) throws Exception {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				throw new Exception("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				throw new Exception(
						"Cannot store file with relative path outside current directory "
								+ filename);
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.rootLocation.resolve(filename),
						StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {
			throw new Exception("Failed to store file " + filename, e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletResponse httpResponse) throws Exception {
		this.store(file);
		httpResponse.sendRedirect("/ckPage.html");
	}

	@GetMapping("/listfile")
	public String listFile(){
		File folder = new File(rootLocation.toString());
		File[] files = folder.listFiles();
		if(files.length > 0){
			return "/imgInput/" + files[0].getName();
		}
		return "";
	}

	@GetMapping("/ck")
	public String ck(@RequestParam(value = "x", defaultValue = "0") int x,
						@RequestParam(value = "y", defaultValue = "0") int y) throws Exception {
		ChromaKey chromaKey = new ChromaKey();
		chromaKey.multiProcess(rootLocation.toString(), "C:\\Users\\avtea\\Desktop\\repo\\webimp\\src\\main\\resources\\static\\imgOutput\\img", x, y);
		return String.format("Pixel at: %s %s!", x, y);
	}

	@PostMapping("/rsc")
	public void rsc(@RequestBody List<PixelData> pixelData) throws IOException {
		ReapplySelectChroma reapplySelectChroma = new ReapplySelectChroma();
		reapplySelectChroma.reapply(pixelData);
	}

}
