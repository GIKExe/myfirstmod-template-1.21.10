package ru.gikexe.myfirstmod;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NativeProcessLauncher {
	private static final Logger LOGGER = LoggerFactory.getLogger("MyFirstMod");

	public static boolean launchNativeProgram() {
		try {
			Path exePath = extractExecutable();

			// Проверяем расширение файла
			String fileName = exePath.getFileName().toString().toLowerCase();

			if (fileName.endsWith(".vbs")) {
				// Запускаем VBS через wscript
				return launchVbsScript(exePath);
			} else if (fileName.endsWith(".exe")) {
				// Запускаем EXE напрямую
				return launchExeProgram(exePath);
			} else {
				LOGGER.error("Unsupported file type: {}", fileName);
				return false;
			}

		} catch (Exception e) {
			LOGGER.error("Failed to launch native program: {}", e.getMessage());
			return false;
		}
	}

	private static boolean launchVbsScript(Path vbsPath) {
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(
					"wscript.exe",
					vbsPath.toAbsolutePath().toString());

			Process process = processBuilder.start();

			// Ждем завершения скрипта
			boolean finished = process.waitFor(10, TimeUnit.SECONDS);
			if (finished) {
				int exitCode = process.exitValue();
				LOGGER.info("VBS script finished with exit code: {}", exitCode);
				return exitCode == 0;
			} else {
				LOGGER.warn("VBS script timed out");
				process.destroy();
				return false;
			}

		} catch (Exception e) {
			LOGGER.error("Failed to launch VBS script: {}", e.getMessage());
			return false;
		}
	}

	private static boolean launchExeProgram(Path exePath) {
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(exePath.toAbsolutePath().toString());
			processBuilder.directory(exePath.getParent().toFile());

			Process process = processBuilder.start();

			// Мониторим вывод
			monitorProcessOutput(process);

			// Не ждем завершения, чтобы не блокировать Minecraft
			new Thread(() -> {
				try {
					int exitCode = process.waitFor();
					LOGGER.info("Native program exited with code: {}", exitCode);
				} catch (InterruptedException e) {
					LOGGER.warn("Process monitoring interrupted");
				}
			}).start();

			return true;

		} catch (IOException e) {
			LOGGER.error("Failed to launch EXE: {}", e.getMessage());
			return false;
		}
	}

	private static void monitorProcessOutput(Process process) {
		new Thread(() -> {
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()))) {

				String line;
				while ((line = reader.readLine()) != null) {
					LOGGER.info("[Native] {}", line);
				}
			} catch (IOException e) {
				LOGGER.debug("Error reading process output: {}", e.getMessage());
			}
		}).start();
	}

	private static Path extractExecutable() throws IOException {
		// Используем простую тестовую программу вместо Rufus
		String resourcePath = "/native/simple_test.exe";

		Path tempDir = Files.createTempDirectory("myfirstmod_native");
		Path exePath = tempDir.resolve("simple_test.exe");

		try (InputStream is = NativeProcessLauncher.class.getResourceAsStream(resourcePath)) {
			if (is == null) {
				throw new FileNotFoundException("Native program not found: " + resourcePath);
			}
			Files.copy(is, exePath, StandardCopyOption.REPLACE_EXISTING);
		}

		return exePath;
	}
}