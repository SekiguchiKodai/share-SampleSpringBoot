package com.example.demo.t.dbunit;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvURLDataSet;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

public class CsvDataSetLoader extends AbstractDataSetLoader {

	@Override
	protected IDataSet createDataSet(Resource resource) throws Exception {
		// CSVファイルを読込みデータセットオブジェクトに変換する
		return new CsvURLDataSet(resource.getURL());
	}

	
}
