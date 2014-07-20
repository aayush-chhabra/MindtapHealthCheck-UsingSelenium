phantom.casperPath = '/usr/local/Cellar/casperjs/1.1-beta3/libexec/';
phantom.injectJs(phantom.casperPath + '/bin/bootstrap.js');

phantom.casperTest = true;

var lpnOnThePage = [];
var x = require('casper').selectXPath;
var webPage = require('webpage').create(),
    system = require('system'),
    casper = require('casper').create();
var utils = require('utils');

    casper.on('remote.message', function(message) {
    	console.log(message);
    });

	casper.start('http://qae-ng.cengage.com/static/nb/login.html', function() {
        this.echo(this.getTitle());
    });

    casper.then(function(){
             //this.echo(this.getCurrentUrl());
        this.sendKeys({
                    type: 'xpath',
                    path: ".//*[@id='_username_id']"
                }, 'sanat.chugh@cengage.com');
                
                this.sendKeys({
                    type: 'xpath',
                    path: ".//*[@id='_password_id']"
                }, 'Cengage1');
        });

    casper.then(function(){
                    this.test.assertExists({
                        type: 'xpath',
                        path: ".//*[@id='loginForm']/div/div[2]/p/input"
                    }, "Good, the input item actually exist");
                   this.thenClick(x(".//*[@id='loginForm']/div/div[2]/p/input"));
            });

    
    casper.waitUntilVisible(x(".//*[@id='masters']/div[5]/div[3]/ul[1]/li[1]/div/div[4]/a"), function() {

            //have been calculating the time till this step in casp.js

            //now calculating time to open a reading activity.

    		this.thenClick(x(".//*[@id='masters']/div[5]/div[3]/ul[1]/li[1]/div/div[4]/a")).thenEvaluate(function(){
                console.log("a");
            });

    		casper.waitUntilVisible(".lpn_name", function() {

	          //       this.evaluate(function() {
	          //           lpnOnThePage = __utils__.findAll('.lpn_name a');
	          //           console.log(lpnOnThePage);

	        		// });

    		// var href = this.evaluate(function() {
		    //     return __utils__.findOne('.lpn_name a').getAttribute('href');
		    // });

    		this.capture('google.png', this.getElementBounds(".lpn_stacklist"));

            var firstRow = this.evaluate(function () {
                var elements = __utils__.findAll('.lpn_name a');
                return [].map.call(elements, function(element) {
                    return element.outerHTML;
                });
            });

            //utils.dump(firstRow);
            console.log(firstRow);



		    this.thenClick(".lpn_name a");

		    

    		
    			// console.log("passed!!");
    		casper.wait(1000,function(){
        		
                this.capture('google1.png', this.getElementBounds(".lpn_stacklist")); 
			
            });
    		
    		});
    });


    casper.then(function(){
        this.test.assertExists(".lpn_name","The sublinks are available");
        this.thenClick(".lpn_name a");
        console.log("passed!!");
    });

    casper.run();

