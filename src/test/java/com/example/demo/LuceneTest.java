package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LuceneTest {

	@Autowired UserMapper userMapper;
	
	@Autowired StudentMapper studentMapper;
	
	@Test
	public void contextLoads() {
		/*try {
			System.out.println(studentMapper.getTeachers3(new HashMap()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//System.out.println(userMapper.getUserByName("name1").getUserAge());
	}

	static String LUNCENEDIR="E:\\lucence";
	Analyzer ikanalyzer = new IKAnalyzer();
	 //指定分词器  标准分词器
	Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
	
	
	
	@Test
	public void testCreateIndex() {		  
      try {
        	//索引库的存储目录
			Directory directory = FSDirectory.open(new File(LUNCENEDIR));			
			 //关联当前lucence版本和分值器
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, ikanalyzer);
            //传入目录和分词器
            IndexWriter iwriter = new IndexWriter(directory, config);
            //获取数据内容
            List<Map> lists=studentMapper.getTeachers3(new HashMap());
            for(Map obj: lists) {
            	Document doc = new Document();
            	//[{teacher=com.example.demo.entity.Teacher@3a16984c, name=name1, age=111}, {teacher=com.example.demo.entity.Teacher@3db1ce78, name=name2, age=222}]
            	Field nameF = new Field("nameF", obj.get("name").toString(),StringField.TYPE_STORED);
            	Field ageF = new Field("ageF", obj.get("age").toString(),StringField.TYPE_STORED);
            	doc.add(nameF);
            	doc.add(ageF);
            	//写入到目录文件中
                iwriter.addDocument(doc);
            }          
            //提交事务
            iwriter.commit();
            //关闭流
            iwriter.close();
            System.out.println("haojigee");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void findNameIndex() {
			
		try {
			Directory directory = FSDirectory.open(new File(LUNCENEDIR));
			// 读取索引库的存储目录
			DirectoryReader ireader = DirectoryReader.open(directory);
			// 搜索类
			IndexSearcher isearcher = new IndexSearcher(ireader);
			// lucence查询解析器，用于指定查询的属性名和分词器
			// 传入 Filed 名称
			QueryParser parser = new QueryParser(Version.LUCENE_47, "nameF", ikanalyzer);
			// 搜索
			// 传入要查询的值
			
			Query query = parser.parse("name1");
			//Filter filter = new DuplicateFilter("nameF");
			//Query query =new FilteredQuery(query, filter);
			
			
			// 最终被分词后添加的前缀和后缀处理器，默认是粗体<B></B>
			SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter("<font color=red>", "</font>");
			// 高亮搜索的词添加到高亮处理器中
			Highlighter highlighter = new Highlighter(htmlFormatter, new QueryScorer(query));
			// 获取搜索的结果，指定返回document返回的个数
			// ScoreDoc[] hits = isearcher  TopDocs topDocs = isearcher.search(query,filter, 9999);
			TopDocs topDocs = isearcher.search(query, Integer.MAX_VALUE);
			ScoreDoc[] hits = topDocs.scoreDocs;
			//List<Map> list=new ArrayList<Map>();
			 //遍历，输出
            for (int i = 0; i < hits.length; i++) {
                int id = hits[i].doc;
                Document hitDoc = isearcher.doc(hits[i].doc);
                String nameF=hitDoc.get("nameF");
                //将查询的词和搜索词匹配，匹配到添加前缀和后缀
                TokenStream tokenStream = TokenSources.getAnyTokenStream(isearcher.getIndexReader(), id, "nameF", ikanalyzer);
                //传入的第二个参数是查询的值
                TextFragment[] frag = highlighter.getBestTextFragments(tokenStream, nameF, false, 10);
                String nameValue="";
                for (int j = 0; j < frag.length; j++) {
                  if ((frag[j] != null) && (frag[j].getScore() > 0)) {
                      nameValue=((frag[j].toString()));
                  }
                }
                System.out.println(nameValue);
               // list.add(map);
            }
			ireader.close();
			directory.close();
			System.out.println("---end");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	@Test
	public void updateIndex() throws Exception {
		//索引库的存储目录
		Directory directory = FSDirectory.open(new File(LUNCENEDIR));			
		// 更新索引
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, ikanalyzer);
		//传入目录和分词器
        IndexWriter iwriter = new IndexWriter(directory, config);
		Document doc = new Document();
		doc.add(new TextField("id", "51173", Field.Store.YES));
		doc.add(new TextField("name", "神鞭，鞭没了，神还在", Field.Store.YES));
		doc.add(new TextField("category", "道具", Field.Store.YES));
		doc.add(new TextField("price", "998", Field.Store.YES));
		doc.add(new TextField("place", "南海群岛", Field.Store.YES));
		doc.add(new TextField("code", "888888", Field.Store.YES));
		doc.add(new StringField("nameF", "1111", Field.Store.YES));
		iwriter.updateDocument(new Term("nameF", "name1"), doc );
		iwriter.commit();
		iwriter.close();
		System.out.println("111111111111");
		
	}
	
	
	
	
	
	
	
}
