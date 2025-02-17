package com.example.demo.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.SampleSpringBootApplication;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = SampleSpringBootApplication.class)
public abstract class AbstractControllerTest {

	/** MockMvc */
	protected final MockMvc mockMvc;
	
	/** テスト用リソースのパス */
	protected static Path resourcesPath;
	
	/** テスト結果出力用ストリーム */
	protected BufferedWriter fileWriter;
	
	public AbstractControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}
	
	/** 
	 * テストリソースが存在するディレクトリまでのパスを取得
	 * @param commonPath 共通パス
	 * @param middlePath 共通パスから後ろのパス
	 */
	@BeforeAll
	public static void beforeAll(
			@Value("${test.resources.path.common}") String commonPath,
			@Value("${test.resources.path.controller}") String middlePath,
			TestInfo info) {
		
		String className = info.getTestClass().get().getSimpleName();
		String lowerTargetClassName = className.replace("Test", "").toLowerCase();
		resourcesPath = Path.of("").toAbsolutePath().resolve(Path.of(commonPath, middlePath, lowerTargetClassName));
	}
	
	/**
	 * テストで生成されるリクエスト・レスポンス情報を出力するため、出力ストリームを生成。
	 * @param info
	 * @throws IOException
	 */
	@BeforeEach
	public void beforeEach(TestInfo info) throws IOException {
		Path outDirPath = resourcesPath.resolve(Path.of(info.getDisplayName())); 
		Path outFullPath = outDirPath.resolve(Path.of(info.getDisplayName()+"_result.txt"));
		// テスト単位でディレクトリ作成
		if (Files.notExists(outDirPath)) {
			Files.createDirectory(outDirPath);
		}
		fileWriter = Files.newBufferedWriter(outFullPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}
	
	/**
	 * テストで生成されるリクエスト・レスポンス情報を出力し、出力ストリームを閉じる
	 * @throws IOException
	 */
	@AfterEach
	public void afterEach() throws IOException {
		if (Objects.nonNull(fileWriter)) {
			fileWriter.flush();
			fileWriter.close();
		}
	}
	
}
