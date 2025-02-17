package com.example.demo.mapper;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.SampleSpringBootApplication;
import com.example.demo.test.dbunit.CsvDataSetLoader;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class
})
@Transactional	//Test実施後、ロールバックしてDBの汚染を防ぐ
//SpringBootTest.WebEnvironment.NONE Webサーバを起動せずテストを実施する
@SpringBootTest(classes = SampleSpringBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public abstract class AbstractMapperTest {
	
	/** テスト用リソースのパス */
	protected static Path resourcesPath;
	
	/** テスト結果出力用ストリーム */
	protected BufferedWriter fileWriter;
	
	/** 
	 * テストリソースが存在するディレクトリまでのパスを取得
	 * @param commonPath 共通パス
	 * @param middlePath 共通パスから後ろのパス
	 */
	@BeforeAll
	public static void beforeAll(
			@Value("${test.resources.path.common}") String commonPath,
			@Value("${test.resources.path.mapper}") String middlePath,
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
