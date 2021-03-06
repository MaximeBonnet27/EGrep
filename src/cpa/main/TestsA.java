package cpa.main;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cpa.algorithmes.Determinisation;
import cpa.algorithmes.EpsilonTransitions;
import cpa.automate.Automate;
import cpa.factory.AutomateFactory;
public class TestsA {

  @Test
  public void testA(){
    Automate a = AutomateFactory.createAutomateExempleA();
    a = AutomateFactory.traitement(a);
    assertTrue(a.verifierMot("ab"));
    assertFalse(a.verifierMot("a"));
    assertTrue(a.verifierMot("abb"));
    assertTrue(a.verifierMot("bab"));
  }
  
//  @Test
//  public void testB(){
//    Automate a = AutomateFactory.createAutomateExempleB();
//    assertTrue(a.verifierMot("ca"));
//    assertTrue(a.verifierMot("aba"));
//    assertFalse(a.verifierMot("cb"));
//    assertTrue(a.verifierMot("cbaaabcaba"));
//  }
//  
  @Test
  public void testC(){
    Automate a = AutomateFactory.createAutomateExempleC();
    a = AutomateFactory.traitement(a);
    assertTrue(a.verifierMot("ab"));
    assertFalse(a.verifierMot("baaaa"));
    assertTrue(a.verifierMot("bbbbaaaaaaab"));
  }
  
//  @Test
//  public void testD(){
//    Automate a = AutomateFactory.createAutomateExempleD();
//    assertTrue(a.verifierMot("ca"));
//    assertTrue(a.verifierMot("aba"));
//    assertFalse(a.verifierMot("cb"));
//    assertTrue(a.verifierMot("cbaaabcaba"));
//    assertTrue(a.verifierMot("ab"));
//    assertFalse(a.verifierMot("baaaa"));
//    assertTrue(a.verifierMot("bbbbaaaaaaab"));
//  }
  
  @Test
  public void testE(){
    Automate a = AutomateFactory.createAutomateExempleE();
    a = AutomateFactory.traitement(a);
    assertTrue(a.verifierMot("a"));
    assertTrue(a.verifierMot("aaaaaaaaaaaaaaaaaaaaaaa"));
    assertTrue(a.verifierMot("bb"));
    assertTrue(a.verifierMot("bbbaaaab"));
  }
  
  @Test
  public void testF(){
    Automate a = AutomateFactory.createAutomateExempleF();
    a = AutomateFactory.traitement(a);
    System.out.println(a);
    assertTrue(a.verifierMot("F"));
    assertTrue(a.verifierMot("aaaF"));
  }
  @Test
  public void testG(){
    Automate a = AutomateFactory.createAutomateExempleG();
    a = AutomateFactory.traitement(a);
    assertTrue(a.verifierMot("ADDDDDDD"));
    assertTrue(a.verifierMot("AD"));
    assertFalse(a.verifierMot("A"));
  }
  @Test
  public void testH(){
    Automate a = AutomateFactory.createAutomateExempleH();
    a = AutomateFactory.traitement(a);
    assertTrue(a.verifierMot("ADB"));
    assertTrue(a.verifierMot("AB"));
  }
  @Test
  public void testI(){
    Automate a = AutomateFactory.createAutomateExempleI();
    assertTrue(a.verifierMot("BA"));
    assertFalse(a.verifierMot("A"));
  }
  @Test
  public void testJ(){
    Automate a = AutomateFactory.createAutomateExempleJ();
    a = AutomateFactory.traitement(a);
    assertTrue(a.verifierMot("aa"));
    assertFalse(a.verifierMot("a"));
    assertTrue(a.verifierMot("baab"));
  }
  
  @Test
  public void testK(){
    Automate a = AutomateFactory.createAutomateExempleK();
    a = AutomateFactory.traitement(a);
    assertTrue(a.verifierMot("bbMMM"));
    assertFalse(a.verifierMot("bMb"));
    assertTrue(a.verifierMot("bbMMMMbb"));
  }
  
  @Test
  public void testL(){
    Automate a = AutomateFactory.createAutomateExempleL();
    a = AutomateFactory.traitement(a);
    assertTrue(a.verifierMot("AAAb"));
    assertTrue(a.verifierMot("AAAAAAAAAAAb"));
    assertFalse(a.verifierMot("AAb"));
  }

  @Test
  public void testM(){
    Automate a = AutomateFactory.createAutomateExempleM();
    a = AutomateFactory.traitement(a);
    assertTrue(a.verifierMot("AAAb"));
    assertTrue(a.verifierMot("AAAAAAAAAAAb"));
    assertFalse(a.verifierMot("AAb"));
    assertTrue(a.verifierMot("AAAAAAb"));
  }
  @Test 
  public void testN(){
    Automate a = AutomateFactory.createAutomateExempleN();
    a = AutomateFactory.traitement(a);
    assertTrue(a.verifierMot("AxB"));
    assertFalse(a.verifierMot("AB"));
    assertFalse(a.verifierMot("AhhB"));
  }
  
  @Test 
  public void testO(){
    Automate a = AutomateFactory.createAutomateExempleO();
    a = AutomateFactory.traitement(a);
    assertTrue(a.verifierMot("AxB"));
    assertFalse(a.verifierMot("BA"));
  }
  
  @Test 
  public void testP(){
    Automate a = AutomateFactory.createAutomateExempleP();
    a = AutomateFactory.traitement(a);
    assertTrue(a.verifierMot("BA"));
    assertFalse(a.verifierMot("BAB"));
    assertTrue(a.verifierMot("QSQSDAAQSMDLKQSDMA"));
  }

  @Test 
  public void testQ(){
	    Automate a = AutomateFactory.createAutomateExempleQ();
	    a = AutomateFactory.traitement(a);
	    assertTrue(a.verifierMot("aqsdqsd"));
	    assertTrue(a.verifierMot("eddd"));
	    assertFalse(a.verifierMot("qmdlqsd"));
  }
  @Test 
  public void testR(){
	    Automate a = AutomateFactory.createAutomateExempleR();
	    a = AutomateFactory.traitement(a);
	    assertFalse(a.verifierMot("kevz"));
	    assertTrue(a.verifierMot("bonnet"));
	    assertTrue(a.verifierMot("qsdlzqsdqsdk"));
	    assertTrue(a.verifierMot("vinke"));
  }
  
  
}
