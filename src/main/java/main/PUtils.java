package main;

import java.util.List;

import static main.Main.js;


public class PUtils{

	public Object getResponse() {
		Object obj = js.executeScript("return window.varTemp");
		return obj;
	}

	public void resetVarTemp() {
		js.executeScript("window.varTemp = []");
	}

	public void initListenerComplete() {
		js.executeScript("window.varTemp = []");
		js.executeScript(
				"window.addEventListener('message', function regular(event) { window.varTemp.push(event.data.method);})");
	}

	public boolean checkResponse(String expoectedResponse) {

		List<String> list = convertObjectToList(getResponse());

		for (String s : list) {
			if (s.contains(expoectedResponse)) {
				return true;
			}
		}
		return false;

	}

	public boolean checkIfPageLoaded() {
		return js.executeScript("return document.readyState").equals("complete");
	}

	@SuppressWarnings("unchecked")
	public List<String> convertObjectToList(Object obj) {
		return (List<String>) obj;
	}

}
