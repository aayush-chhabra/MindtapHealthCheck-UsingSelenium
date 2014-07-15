phantom.casperPath = '/usr/local/Cellar/casperjs/1.1-beta3/libexec/';
phantom.injectJs(phantom.casperPath + '/bin/bootstrap.js');

var casper = require('casper').create();

casper.start('http://qae-ng.cengage.com/static/nb/login.html', function() {
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
 	this.click({
        type: 'xpath',
        path: ".//*[@id='loginForm']/div/div[2]/p/input"
    });
 });

casper.then(function(){
	this.echo(this.getCurrentUrl());
});

casper.run();