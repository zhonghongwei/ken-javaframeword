package com.shine.framework.Lucene;

import java.io.File;
import java.io.FileReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.util.packed.PackedInts.ReaderImpl;

public class LuceneManager {
	private static LuceneManager manager = null;

	public static LuceneManager getManager() {
		if (manager == null)
			manager = new LuceneManager();

		return manager;
	}

	/**
	 * 建立索引
	 * 
	 * @param indexDirPath
	 * @param docmentDirPath
	 */
	public void createIndex(String indexDirPath, String docmentDirPath) {
		createIndex(indexDirPath, docmentDirPath, "utf-8");
	}

	/**
	 * 建立索引
	 * 
	 * @param indexDirPath
	 * @param docmentDirPath
	 * @param ecoding
	 */
	public void createIndex(String indexDirPath, String docmentDirPath,
			String ecoding) {
		try {
			createIndex(indexDirPath, docmentDirPath, ecoding,
					new StandardAnalyzer(Version.LUCENE_36));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 建立索引
	 * 
	 * @param indexDirPath
	 *            索引所放的位置
	 * @param docmentDirPath
	 *            索引文件路径
	 * @param ecoding
	 *            编码
	 * @param luceneAnalyzer
	 *            分析器
	 */
	public void createIndex(String indexDirPath, String docmentDirPath,
			String ecoding, Analyzer luceneAnalyzer) throws Exception {
		// 需要的分词器
		Directory dir = FSDirectory.open(new File(indexDirPath));

		// 创建的是哪个版本的IndexWriterConfig
		IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_36,
				new StandardAnalyzer(Version.LUCENE_36));
		IndexWriter iw = new IndexWriter(dir, conf);

		File file = new File(docmentDirPath);
		for (int i = 0; i < file.listFiles().length; i++) {
			System.out.println("建立文件" + i + 1 + "的索引");
			File f = file.listFiles()[i];
			// 3.创建Document对象
			System.out.println(f);
			Document doc = new Document();
			doc.add(new Field("contents", new FileReader(f.getParentFile())));
			doc.add(new Field("name", f.getName(), Field.Store.YES,
					Field.Index.ANALYZED));
			doc.add(new Field("path", f.getAbsolutePath(), Field.Store.YES,
					Field.Index.NOT_ANALYZED));
			iw.addDocument(doc);
		}
		System.out.println("建立索引完毕");
		if (iw != null) {
			iw.close();
		}

	}

	/**
	 * 简单搜索
	 * 
	 * @param indexDirPath
	 * @param luceneAnalyzer
	 * @param keyWord
	 */
	public void simpleQuery(String indexDirPath, Analyzer luceneAnalyzer,
			String keyWord) throws Exception {
		Directory dir = FSDirectory.open(new File(indexDirPath));
		IndexReader ir = IndexReader.open(dir);
		IndexSearcher srch = new IndexSearcher(ir);
		QueryParser parser = new QueryParser(Version.LUCENE_36, "name",
				luceneAnalyzer);
		Query query = parser.parse(keyWord);
		TopDocs tds = srch.search(query, 10000);
		ScoreDoc[] sds = tds.scoreDocs;
		System.out.println(sds.length);
		for (int i = 0; i < sds.length; i++) {
			// 7.根据Searcher和ScoreDoc获取具体Document
			Document doc = srch.doc(sds[i].doc);
			// 8.根据Document获取需要的值
			System.out.println(doc.get("name") + " | " + doc.get("path"));
		}
		if (ir != null) {
			ir.close();
		}
		if (srch != null) {
			srch.close();
		}

	}

	public static void main(String[] args) {

		try {
			// new
			// LuceneManager().createIndex("C:/Users/Dragon/Desktop/Lucene/data/index",
			// "C:/Users/Dragon/Desktop/Lucene/data/documents");
			new LuceneManager().simpleQuery(
					"C:/Users/Dragon/Desktop/Lucene/data/index",
					new StandardAnalyzer(Version.LUCENE_36), "2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
