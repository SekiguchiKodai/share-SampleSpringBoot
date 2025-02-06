package com.example.demo.mapper;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.SampleSpringBootApplication;
import com.example.demo.dbunit.CsvDataSetLoader;
import com.example.demo.entity.VAddress;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class
})
@Transactional
@SpringBootTest(classes = SampleSpringBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class VAddressMapperTest {
	
	private final VAddressMapper vAddressMapper;
	@Autowired
	public VAddressMapperTest(VAddressMapper vAddressMapper) {
		this.vAddressMapper = vAddressMapper;
	}
	
	@Test
	@DatabaseSetup(value = "/com/example/demo/mapper/vaddressmapper/setup/")
	public void findByPrefectureメソッドを実行して市区町村情報を取得() {
		List<String> expectedCities = Arrays.asList("京都市北区","京都市上京区", "京都市左京区","京都市右京区");
		
		String prefectureName = "京都府";
		List<VAddress> actualAddress = vAddressMapper.findByPrefectureName(prefectureName);
		
		List<String> actualCities = new ArrayList<String>();
		for (VAddress vAddress : actualAddress) {
			actualCities.add(vAddress.getCityName());
		}
		
		assertThat(actualCities).containsAll(expectedCities);
	}
}
