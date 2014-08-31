"use strict";

describe("navigate this app", function() {

	browser.get("index.html");

	it("should redirect index.html to index.html#/welcome", function() {
		browser.setLocation("/index.html");
		expect(browser.getCurrentUrl()).toMatch("/welcome");
	});

});