package com.example.demo.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.SampleSpringBootApplication;
import com.example.demo.test.dbunit.CsvDataSetLoader;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class
})
@Transactional
@SpringBootTest(classes = SampleSpringBootApplication.class, webEnvironment = WebEnvironment.NONE)
public abstract class BaseServiceTest {
	
	/** テスト用リソースのパス */
	protected static Path resourcesDirPath;
	
	/** テストエビデンスのパス */
	protected static Path evidenceDirPath;
	
	/** テスト結果出力用ストリーム */
	protected BufferedWriter fileWriter;
	
	/** 
	 * テストリソースが存在するディレクトリまでのパスを取得
	 * @param commonPath 共通パス
	 * @param middlePath 共通パスから後ろのパス
	 * @throws IOException 
	 */
	@BeforeAll
	public static void beforeAll(
			@Value("${test.resources.path.common}") String commonPath,
			@Value("${test.resources.path.service}") String middlePath,
			TestInfo info) throws IOException {
		
		String className = info.getTestClass().get().getSimpleName();
		String lowerTargetClassName = className.replace("Test", "").toLowerCase();
		resourcesDirPath = Path.of("").toAbsolutePath().resolve(Path.of(commonPath, middlePath, lowerTargetClassName));
		
		if (Files.notExists(resourcesDirPath)) {
			Files.createDirectory(resourcesDirPath);
		}
		
		evidenceDirPath = resourcesDirPath.resolve("evidence");
		if (Files.notExists(evidenceDirPath)) {
			Files.createDirectory(evidenceDirPath);
		}
	}
	
	/**
	 * テストで生成されるリクエスト・レスポンス情報を出力するため、出力ストリームを生成。
	 * @param info
	 * @throws IOException
	 */
	@BeforeEach
	public void beforeEach(TestInfo info) throws IOException {
		// テスト対象のクラスのメソッド毎にディレクトリを用意
		String nestedClassName   = info.getTestClass().get().getSimpleName().replaceAll("^_", "");
		Path outDirPath = evidenceDirPath.resolve(nestedClassName);
		if (Files.notExists(outDirPath)) {
			Files.createDirectory(outDirPath);
		}
		
		// エビデンスファイルの初期化
		String methodName = info.getTestMethod().get().getName();
		String fileName   = methodName + "_" + info.getDisplayName() + "_result.txt";
		Path outFilePath = outDirPath.resolve(fileName);
		fileWriter = Files.newBufferedWriter(outFilePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
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
