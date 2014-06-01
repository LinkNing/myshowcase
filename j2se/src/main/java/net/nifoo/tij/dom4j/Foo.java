package net.nifoo.tij.dom4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Text;
import org.dom4j.VisitorSupport;
import org.dom4j.io.DOMReader;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Foo {

	public static void main(String[] args) {
		try {
			new Foo().test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test() throws MalformedURLException, DocumentException, SAXException {
		String fileName = "net/nifoo/tij/dom4j/books.xml";
		String xsd = "net/nifoo/tij/dom4j/books.xsd";
		URL url = Foo.class.getClassLoader().getResource(fileName);
		File file = new File(url.getPath());

		Document doc = null;
		//		doc = readBySax(file);
		//		doc = readByDom(file);
		// 		doc = createDocument();
		//		doc = parseText();
		doc = readAndValid(fileName, xsd);

		doc.accept(new MyVisitor());

		modDoc(doc);
		//outputXml(doc, new OutputStreamWriter(System.out));
	}

	// 以SAX方式从文件读取XML
	public Document readBySax(File file) throws MalformedURLException, DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		return document;
	}

	// 以DOM方式从文件读取XML
	public Document readByDom(File file) {
		Document document = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();

			DOMReader reader = new DOMReader();
			document = reader.read(builder.parse(file));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return document;
	}

	//通过API创建Doc
	public Document createDocument() {
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");

		Element root = document.addElement("books");

		/* 加入一行注释 */
		root.addComment("This is a test for dom4j!");

		/* 加入book节点 */
		Element element = root.addElement("book");
		element.addAttribute("show", "yes").addElement("title").setText("Dom4j Tutorials");

		element = root.addElement("book");
		element.addAttribute("show", "yes").addElement("title").setText("Lucene Studing");

		element = root.addElement("book");
		element.addAttribute("show", "no").addElement("title").setText("Lucene in Action");

		/* 加入owner节点 */
		Element ownerElement = root.addElement("owner");
		ownerElement.setText("O'Reilly");

		return document;
	}

	//从字符串中解析XML
	public Document parseText() throws DocumentException {
		StringBuilder text = new StringBuilder();
		text.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		text.append("<books>");
		text.append("    <!--This is a test for dom4j, holen, 2004.9.11-->");
		text.append("    <book show=\"yes\">");
		text.append("       <title>Dom4j Tutorials</title>");
		text.append("    </book>");
		text.append("    <book show=\"yes\">");
		text.append("       <title>Lucene Studing</title>");
		text.append("    </book>");
		text.append("    <book show=\"no\">");
		text.append("       <title>Lucene in Action</title>");
		text.append("    </book>");
		text.append("    <owner>O'Reilly</owner>");
		text.append("</books>");

		Document document = DocumentHelper.parseText(text.toString());
		return document;
	}

	/**
	 *  XML Schema验证
	 */
	public Document readAndValid(String fileName, final String xsd) throws DocumentException, SAXException {
		SAXReader reader = new SAXReader(true);

		EntityResolver resolver = new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) {
				InputStream in = Foo.class.getClassLoader().getResourceAsStream(xsd);
				return new InputSource(in);
			}
		};
		reader.setEntityResolver(resolver);
		reader.setFeature("http://xml.org/sax/features/validation", true);
		reader.setFeature("http://apache.org/xml/features/validation/schema", true);
		reader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
		reader.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation",xsd);  

		Document doc = reader.read(Foo.class.getClassLoader().getResourceAsStream(fileName));

		return doc;
	}

	/**
	 * 获取Root元素
	 */
	public Element getRootElement(Document doc) {
		return doc.getRootElement();
	}

	/**
	 * 修改XML文件中内容.
	 * 重点掌握dom4j中如何添加节点,修改节点,删除节点
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void modDoc(Document document) {
		/** 修改内容之一: 如果book节点中show属性的内容为yes,则修改成no */
		/** 先用xpath查找对象 */
		List list = document.selectNodes("/books/book/@show");
		for (Attribute attribute : (List<Attribute>) list) {
			if (attribute.getValue().equals("yes")) {
				attribute.setValue("no");
			}
		}

		/**
		 * 修改内容之二: 把owner项内容改为Tshinghua
		 * 并在owner节点中加入date节点,date节点的内容为2004-09-11,还为date节点添加一个属性type
		 */
		Element ownerElement = (Element) document.selectSingleNode("/books/owner");
		ownerElement.setText("Tshinghua");
		Element dateElement = ownerElement.addElement("date");
		dateElement.setText("2004-09-11");
		dateElement.addAttribute("type", "Gregorian calendar");

		/** 修改内容之三: 若title内容为Dom4j Tutorials,则删除该节点 */
		list = document.selectNodes("/books/book");
		for (Element bookElement : (List<Element>) list) {
			Iterator iterator = bookElement.elementIterator("title");
			while (iterator.hasNext()) {
				Element titleElement = (Element) iterator.next();
				if (titleElement.getText().equals("Dom4j Tutorials")) {
					bookElement.remove(titleElement);
				}
			}
		}
	}

	public void outputXml(Document document, String filename) throws IOException {
		outputXml(document, new FileWriter(new File(filename)));
	}

	/**
	 * 格式化XML文档,并解决中文问题
	 * @throws IOException 
	 */
	public void outputXml(Document document, Writer writer) {

		OutputFormat format = OutputFormat.createPrettyPrint(); //格式化输出,类型IE浏览一样
		//format = OutputFormat.createCompactFormat(); // 缩减格式
		format.setEncoding("UTF-8");

		XMLWriter out = new XMLWriter(writer, format);
		try {
			out.write(document);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}

	public Document styleDocument(Document document, String stylesheet) throws Exception {
		// load the transformer using JAXP
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(new StreamSource(stylesheet));
		// now lets style the given document
		DocumentSource source = new DocumentSource(document);
		DocumentResult result = new DocumentResult();
		transformer.transform(source, result);
		// return the transformed document
		Document transformedDoc = result.getDocument();
		return transformedDoc;
	}

	/**
	 * 通过 visitor遍历所有子节点.
	 * 调用：  root.accept(new MyVisitor())
	 */
	public class MyVisitor extends VisitorSupport {
		public void visit(Element element) {
			System.out.println(element.getName());
		}

		public void visit(Attribute attr) {
			System.out.println(attr.getName());
		}

		public void visit(Text text) {
			System.out.println(text.getText());
		}
	}

}