package javaitzen.drools;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Before;
import org.junit.Test;

public class TestBasicRules {

	private KnowledgeBase kbase;
	private Logger javaLogger = Logger.getLogger("testLogger");
	
	@Before
	public void setup() {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("basic.drl"), ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error: errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		
		kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
					
		
	}
	
	@Test
	public void testBasic() {

		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

		RoolVO vo = new RoolVO();
		vo.setStringValue("Learning to drool");
		vo.setBooleanValue(true);
		ksession.insert(vo);
		ksession.fireAllRules();
		checkDone(ksession); 

		
	}

	@Test
	public void testMore1() {

		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

		RoolVO vo = new RoolVO();
		vo.setStringValue("Learning to drool");
		vo.setDoubleValue(11.0);
		
		ksession.insert(vo);
		ksession.fireAllRules();
		
		checkDone(ksession); 
		
	}
	
	
	@Test
	public void testMore2() {

		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

		RoolVO vo = new RoolVO();
		vo.setStringValue("Learning to drool");
		vo.setBooleanValue(false);
		vo.setListValue(new ArrayList<String>());
		
		ksession.insert(vo);
		ksession.fireAllRules();
		checkDone(ksession); 		
		
	}
	
	@Test
	public void testGlobal() {

		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

		RoolVO vo = new RoolVO();
		vo.setStringValue("Global");
		ksession.insert(vo);
		ksession.setGlobal("logger", javaLogger);
		ksession.fireAllRules();
		checkDone(ksession); 		
		
	}

	@Test
	public void testRegex() {

		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

		RoolVO vo = new RoolVO();
		vo.setStringValue("21/11/2011");
		ksession.insert(vo);
		ksession.setGlobal("logger", javaLogger);
		ksession.fireAllRules();
		checkDone(ksession); 		
		
	}
	
	@Test
	public void testList() {

		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

		RoolVO vo = new RoolVO();
		ArrayList<String> list = new ArrayList<String>();
		list.add("this");
		list.add("is");
		list.add("a");
		list.add("list");
		list.add("of");
		list.add("items");
		vo.setListValue(list);
		ksession.insert(vo);
		ksession.setGlobal("logger", javaLogger);
		ksession.fireAllRules();
		checkDone(ksession); 		
		
	}
	
	private void checkDone(StatefulKnowledgeSession ksession) {
		KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
		for (Object o: ksession.getObjects()) {
			if(o instanceof RoolVO) {
				assertEquals("Done.", ((RoolVO) o).getStringValue());
			}
		}
		logger.close();
	}
	
}
