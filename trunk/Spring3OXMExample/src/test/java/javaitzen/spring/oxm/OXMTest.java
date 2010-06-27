package javaitzen.spring.oxm;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The Class OXMTest.
 */
@ContextConfiguration(locations={"/javaitzen/spring/oxm/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OXMTest {

	@Autowired
	OXMarshall castor;
	
	@Autowired
	OXMarshall jax;

	@Autowired
	OXMarshall xtream;
	
	ClassToXMLMap message;
	
	/**
     * Setup.
     */
	@Before
	public void setup(){
    	message = new ClassToXMLMap();
    	message.setData("I am data");
    	message.setHistory("in the past");
	}
	
	/**
     * Test castor.
     * 
     * @throws Exception
     *             the exception
     */
	@Test
	public void testCastor() throws Exception{
		String castorFile = "castor.xml";
		castor.saveXML(message, castorFile);
        assertNotNull(castor.loadXML(castorFile));
	}
	
	/**
     * Test jax b.
     * 
     * @throws Exception
     *             the exception
     */
	@Test
	public void testJaxB() throws Exception{
		String jaxbFile = "jaxb.xml";
    	jax.saveXML(message,jaxbFile);
        assertNotNull(jax.loadXML(jaxbFile));
	}

	/**
     * Test x stream.
     * 
     * @throws Exception
     *             the exception
     */
	@Test
	public void testXStream() throws Exception{
        String xtreamFile = "xtream.xml";
        xtream.saveXML(message,xtreamFile);
        assertNotNull(xtream.loadXML(xtreamFile));
    }
	
}
