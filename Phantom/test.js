phantom.casperPath = '/usr/local/Cellar/casperjs/1.1-beta3/libexec/';
phantom.injectJs(phantom.casperPath + '/bin/bootstrap.js');


var webPage = require('webpage').create(),
    system = require('system');


if (!Date.prototype.toISOString) {
    Date.prototype.toISOString = function () {
        function pad(n) { return n < 10 ? '0' + n : n; }
        function ms(n) { return n < 10 ? '00'+ n : n < 100 ? '0' + n : n }
        return this.getFullYear() + '-' +
            pad(this.getMonth() + 1) + '-' +
            pad(this.getDate()) + 'T' +
            pad(this.getHours()) + ':' +
            pad(this.getMinutes()) + ':' +
            pad(this.getSeconds()) + '.' +
            ms(this.getMilliseconds()) + 'Z';
    }
}


function createHAR(address, title, startTime, resources)
{
    var entries = [];

    resources.forEach(function (resource) {
        var request = resource.request,
            startReply = resource.startReply,
            endReply = resource.endReply;

        if (!request || !startReply || !endReply) {
            return;
        }

        // Exclude Data URI from HAR file because
        // they aren't included in specification
        if (request.url.match(/(^data:image\/.*)/i)) {
            return;
	}

        entries.push({
            startedDateTime: request.time.toISOString(),
            time: endReply.time - request.time,
            request: {
                method: request.method,
                url: request.url,
                httpVersion: "HTTP/1.1",
                cookies: [],
                headers: request.headers,
                queryString: [],
                headersSize: -1,
                bodySize: -1
            },
            response: {
                status: endReply.status,
                statusText: endReply.statusText,
                httpVersion: "HTTP/1.1",
                cookies: [],
                headers: endReply.headers,
                redirectURL: "",
                headersSize: -1,
                bodySize: startReply.bodySize,
                content: {
                    size: startReply.bodySize,
                    mimeType: endReply.contentType
                }
            },
            cache: {},
            timings: {
                blocked: 0,
                dns: -1,
                connect: -1,
                send: 0,
                wait: startReply.time - request.time,
                receive: endReply.time - startReply.time,
                ssl: -1
            },
            webPageref: address
        });
    });

    return {
        log: {
            version: '1.2',
            creator: {
                name: "PhantomJS",
                version: phantom.version.major + '.' + phantom.version.minor +
                    '.' + phantom.version.patch
            },
            webPages: [{
                startedDateTime: startTime.toISOString(),
                id: address,
                title: title,
                webPageTimings: {
                    onLoad: webPage.endTime - webPage.startTime
                }
            }],
            entries: entries
        }
    };
}


	webPage.address = "http://qae-ng.cengage.com/static/nb/login.html";
    webPage.resources = [];

    webPage.onLoadStarted = function () {
        webPage.startTime = new Date();
    };

    webPage.onResourceRequested = function (req) {
        webPage.resources[req.id] = {
            request: req,
            startReply: null,
            endReply: null
        };
    };

    webPage.onResourceReceived = function (res) {
        if (res.stage === 'start') {
            webPage.resources[res.id].startReply = res;
        }
        if (res.stage === 'end') {
            webPage.resources[res.id].endReply = res;
        }
    };

    webPage.open(webPage.address, function (status) {
        var har;
        if (status !== 'success') {
            console.log('FAIL to load the address');
            phantom.exit(1);
        } else {
            webPage.endTime = new Date();
            webPage.title = webPage.evaluate(function () {
                return document.title;
            });
            har = createHAR(webPage.address, webPage.title, webPage.startTime, webPage.resources);
            //console.log(JSON.stringify(har, undefined, 4));
            
        }
    });

    
    webPage.onLoadFinished = function(status) {
    	
    	var casper = require('casper').create({
			page: webPage,
			verbose: true
		});
    	casper.options.page = webPage;
    	casper.options.page.address = webPage.address;

    	casper.start();

    	casper.then(function(){
    		this.echo(this.getCurrentUrl());
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
    		this.capture('google.png', {
		        top: 100,
		        left: 100,
		        width: 500,
		        height: 400
    		});
    	});
    	
		casper.then(function(){
		 	this.click({
		        type: 'xpath',
		        path: ".//*[@id='loginForm']/div/div[2]/p/input"
		    });
		});

		casper.then(function(){
			this.echo(this.getCurrentUrl());
		});


		casper.run(function(){
			phantom.exit();
			casper.exit();
		});
		
    }




// console.log(webPage.address);
// casper.start();

// casper.then(funciton(){
	
// 	this.sendKeys({
//         type: 'xpath',
//         path: ".//*[@id='_username_id']"
//     }, 'sanat.chugh@cengage.com');
    
//     this.sendKeys({
//         type: 'xpath',
//         path: ".//*[@id='_password_id']"
//     }, 'Cengage1');

// });



// casper.start('http://qae-ng.cengage.com/static/nb/login.html', function(status) {

//     this.sendKeys({
//         type: 'xpath',
//         path: ".//*[@id='_username_id']"
//     }, 'sanat.chugh@cengage.com');
//     this.sendKeys({
//         type: 'xpath',
//         path: ".//*[@id='_password_id']"
//     }, 'Cengage1');
    
// });

//  casper.then(function(){
//  	this.click({
//         type: 'xpath',
//         path: ".//*[@id='loginForm']/div/div[2]/p/input"
//     });
//  });

// casper.then(function(){
// 	this.echo(this.getCurrentUrl());
// 	phantom.exit();
// });

// casper.run();