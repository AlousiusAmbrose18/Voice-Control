package com.jspider.VoiceContrrol;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class App 
{
    @SuppressWarnings("deprecation")
	public static void main( String[] args )
    {
    	Configuration config = new Configuration();
    	
    	config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
    	
    	config.setDictionaryPath("src\\main\\resource\\9278.dic");
    	config.setLanguageModelPath("src\\main\\resource\\9278.lm");

    	try {
			LiveSpeechRecognizer speech = new LiveSpeechRecognizer(config);
			
			speech.startRecognition(true);
			
			SpeechResult speechRes = null;
			
			while ((speechRes = speech.getResult())!=null) {
				
				String voiceCmd = speechRes.getHypothesis();
				System.out.println("voice command is "+ voiceCmd);
				
				if(voiceCmd.equalsIgnoreCase("open chrome"))
				{
					Runtime.getRuntime().exec("cmd.exe /c start chrome www.google.com");
				}
				else if(voiceCmd.equalsIgnoreCase("close chrome"))
				{
					Runtime.getRuntime().exec("cmd.exe /c TASKKILL /F /IM chrome.exe /T>");
				}
					
				
			}
					
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
}

