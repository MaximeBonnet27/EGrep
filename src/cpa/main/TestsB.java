package cpa.main;

import org.junit.Test;

import cpa.automate.Automate;
import cpa.parser.Parser;
import static org.junit.Assert.*;
public class TestsB {

  @Test
  public void testA(){
    Parser parser = new Parser();
    Automate a =  parser.getAutomateFromFile("examples/regex-01.ere");
    assertNotNull(a);
    assertTrue(a.verifierMot("a"));
    assertFalse(a.verifierMot("bbb"));
    assertTrue(a.verifierMot("bba"));
  }

  @Test
  public void testB(){
    Parser parser = new Parser();
    Automate a =  parser.getAutomateFromFile("examples/regex-02.ere");
    assertNotNull(a);
    assertTrue(a.verifierMot("abab"));
    assertFalse(a.verifierMot("a"));
    assertFalse(a.verifierMot("baba"));
  }
  @Test
  public void testC(){
    Parser parser = new Parser();
    Automate a =  parser.getAutomateFromFile("examples/regex-03.ere");
    assertNotNull(a);
    assertTrue(a.verifierMot("bb"));
    assertFalse(a.verifierMot("b"));
    assertTrue(a.verifierMot("cqsdqsbaaaaaaaaaab"));
  }

  @Test
  public void testD(){
    Parser parser = new Parser();
    Automate a =  parser.getAutomateFromFile("examples/regex-04.ere");
    assertNotNull(a);
    assertTrue(a.verifierMot("ababcde"));
    assertTrue(a.verifierMot("abababcde"));
    assertTrue(a.verifierMot("cdabcde"));
    assertTrue(a.verifierMot("abcdabcde"));
  }

  @Test
  public void testE(){
    Parser parser = new Parser();
    Automate a =  parser.getAutomateFromFile("examples/regex-05.ere");
    assertNotNull(a);
    assertTrue(a.verifierMot("ada"));
    assertFalse(a.verifierMot("aa"));
    assertTrue(a.verifierMot("addddddddda"));
  }

  @Test
  public void testF(){
    Parser parser = new Parser();
    Automate a =  parser.getAutomateFromFile("examples/regex-06.ere");
    assertNotNull(a);
    assertTrue(a.verifierMot("ada"));
    assertTrue(a.verifierMot("aa"));
    assertFalse(a.verifierMot("addddddddda"));
  }

  @Test
  public void testG(){
    Parser parser = new Parser();
    Automate a =  parser.getAutomateFromFile("examples/regex-07.ere");
    assertNotNull(a);
    assertTrue(a.verifierMot("baaaabababaz"));
    assertFalse(a.verifierMot("baaaaaabababaz"));

  }

  @Test
  public void testH(){
    Parser parser = new Parser();
    Automate a =  parser.getAutomateFromFile("examples/regex-08.ere");
    assertNotNull(a);
    assertTrue(a.verifierMot("baaaaaaaaaabababaz"));
    assertFalse(a.verifierMot("baabababa"));
  }

  @Test
  public void testI(){
    Parser parser = new Parser();
    Automate a =  parser.getAutomateFromFile("examples/regex-09.ere");
    assertNotNull(a);
    assertTrue(a.verifierMot("abbbbbcd"));
    assertFalse(a.verifierMot("abcd"));
    assertTrue(a.verifierMot("abbbbbcd"));
    assertTrue(a.verifierMot("abbbbbbcd"));
    assertFalse(a.verifierMot("abbbbbbbbbbbbbbcd"));
  }

  @Test
  public void testJ(){
    Parser parser = new Parser();
    Automate a =  parser.getAutomateFromFile("examples/regex-10.ere");
    assertNotNull(a);
    assertTrue(a.verifierMot("axkjqshdlkb"));
    assertFalse(a.verifierMot("ab"));
  }
  @Test
  public void testK(){
    Parser parser = new Parser();
    Automate a =  parser.getAutomateFromFile("examples/regex-11.ere");
    assertNotNull(a);
    assertTrue(a.verifierMot("axkjqshdlkb"));
    assertFalse(a.verifierMot("ba"));
  }

  public void testL(){
    Parser parser = new Parser();
    Automate a =  parser.getAutomateFromFile("examples/regex-12.ere");
    assertNotNull(a);
    assertTrue(a.verifierMot("maxime"));
    assertFalse(a.verifierMot("asqdmaxime"));
    assertFalse(a.verifierMot("maximeqdqds"));
  }
  
  public void testM(){
    Parser parser = new Parser();
    Automate a =  parser.getAutomateFromFile("examples/regex-13.ere");
    assertNotNull(a);
    assertTrue(a.verifierMot("aqsdqsd\\aqdqsd"));
    assertFalse(a.verifierMot("qdlkqsjd"));
  }
  public void testN(){
    Parser parser = new Parser();
    Automate a =  parser.getAutomateFromFile("examples/regex-14.ere");
    assertNotNull(a);
    assertTrue(a.verifierMot("1+2=3.0"));
    assertTrue(a.verifierMot("154654+4=.0"));
    assertFalse(a.verifierMot("13=3.0"));
  }
}
