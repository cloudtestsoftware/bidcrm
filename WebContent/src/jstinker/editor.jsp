<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create a new tinker - EmailTinker</title>
    
    <!-- Latest jQuery from Google CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <!-- Crypto JS -->
     <!--script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/aes.js"></script -->
    <script src="js/rollup/aes.js"></script>
    <!-- Beautify Web - HTML, CSS, JS -->
    <script src="js/beautify-web/beautify-html.js"></script>
    <script src="js/beautify-web/beautify-css.js"></script>
    <script src="js/beautify-web/beautify.js"></script>
    <!-- Ace Editor -->
    <script src="js/ace/ace.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/ace/ext-language_tools.js" type="text/javascript" charset="utf-8"></script>
    <!-- Together -->
    <script src="https://togetherjs.com/togetherjs-min.js"></script>
    <!-- EmailTinker Scripts -->
    <script src="js/jstinker.js"></script>
     <!-- Custom Scripts -->
    <script src="js/jscustom.js"></script>
    <!-- Bootstrap Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- Bootstrap Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <!-- EmailTinker Style -->
    <link rel="stylesheet" href="css/jstinker.css" type="text/css">
    <!-- Bootstrap Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
  	
  	 
    <nav class="navbar navbar-default">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#toolbar">
                <span class="sr-only">Toggle toolbar</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> EmailTinker</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="toolbar">
            <ul class="nav navbar-nav">
                <li id="btnRun"><a href=""><span class="glyphicon glyphicon-play" aria-hidden="true"></span> Run</a></li>
                <li id="btnTidyUp"><a href=""><span class="glyphicon glyphicon-flash" aria-hidden="true"></span> TidyUp</a></li>
                <li id="btnSave"><a href=""><span class="glyphicon glyphicon-save" aria-hidden="true"></span> Save</a></li>
                <li id="btnRefresh"><a href=""><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> Refresh</a></li>
                <li id="btnTogether"><a href=""><span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span> Collaborate</button></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </nav>

    <!-- Sidebar -->
    <div id="sidebar">
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <!-- Frameworks & Extensions -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            Frameworks & Extensions
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        <div class="dropdown">
                            <div class="btn-group">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                                No-Library (pure JS)
                            <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu scrollable-menu" id="dropdownMenu1" role="menu" aria-labelledby="dropdownMenu1">
                                <li role="presentation" class="dropdown-header">jQuery</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">jQuery Compat (edge)</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">jQuery (edge)</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">jQuery 2.1.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">jQuery 2.0.2</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">jQuery 1.11.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">jQuery 1.10.1</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">jQuery 1.9.1</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">jQuery 1.8.3</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">jQuery 1.7.2</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">jQuery 1.6.4</a></li>

                                <li role="presentation" class="dropdown-header">Prototype</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Prototype 1.7.1</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Prototype 1.6.1.0</a></li>

                                <li role="presentation" class="dropdown-header">YUI</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">YUI 3.17.2</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">YUI 3.16.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">YUI 3.14.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">YUI 3.10.1</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">YUI 3.8.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">YUI 3.7.3</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">YUI 3.6.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">YUI 3.5.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">YUI 2.8.0r4</a></li>

                                <li role="presentation" class="dropdown-header">No-Library</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">No-Library (pure JS)</a></li>

                                <li role="presentation" class="dropdown-header">Dojo</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Dojo (nightly)</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Dojo 1.10.1</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Dojo 1.9.4</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Dojo 1.8.7</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Dojo 1.7.6</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Dojo 1.6.2</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Dojo 1.5.3</a></li>

                                <li role="presentation" class="dropdown-header">Processing</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Processing.js 1.4.7</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Processing.js 1.4.1</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Processing.js 1.3.6</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Processing.js 1.2.3</a></li>

                                <li role="presentation" class="dropdown-header">ExtJS</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">ExtJS 4.2.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">ExtJS 4.1.1</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">ExtJS 4.1.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">ExtJS 3.4.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">ExtJS 3.1.0</a></li>

                                <li role="presentation" class="dropdown-header">Raphael</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Raphael 2.1.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Raphael 1.5.2 (min)</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Raphael 1.4</a></li>

                                <li role="presentation" class="dropdown-header">RightJS</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">RightJS 2.3.1</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">RightJS 2.1.1</a></li>

                                <li role="presentation" class="dropdown-header">Three.js</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Three.js r54</a></li>

                                <li role="presentation" class="dropdown-header">Zepto</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Zepto 1.0rc1</a></li>

                                <li role="presentation" class="dropdown-header">Enyo</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Enyo (nightly)</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Enyo 2.5.1</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Enyo 2.4.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Enyo 2.2.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Enyo 2.1</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Enyo 2.0.1</a></li>

                                <li role="presentation" class="dropdown-header">Shipyard</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Shipyard (nightly)</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Shipyard 0.2</a></li>

                                <li role="presentation" class="dropdown-header">Knockout.js</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Knockout.js 3.0.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Knockout.js 2.3.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Knockout.js 2.2.1</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Knockout.js 2.1.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Knockout.js 2.0.0</a></li>

                                <li role="presentation" class="dropdown-header">The X Toolkit</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">The X Toolkit edge</a></li>

                                <li role="presentation" class="dropdown-header">AngularJS</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">AngularJS 1.2.1</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">AngularJS 1.1.1</a></li>

                                <li role="presentation" class="dropdown-header">Ember</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Ember 1.3.1</a></li>

                                <li role="presentation" class="dropdown-header">Underscore</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Underscore 1.4.4</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Underscore 1.4.3</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Underscore 1.3.3</a></li>

                                <li role="presentation" class="dropdown-header">Bonsai</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Bonsai 0.4.1</a></li>

                                <li role="presentation" class="dropdown-header">KineticJS</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">KineticJS 4.3.1</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">KineticJS 4.0.5</a></li>

                                <li role="presentation" class="dropdown-header">FabricJS</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">FabricJS 1.4.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">FabricJS 1.2.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">FabricJS 0.9</a></li>

                                <li role="presentation" class="dropdown-header">qooxdoo</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">qooxdoo 2.1</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">qooxdoo 2.0.3</a></li>

                                <li role="presentation" class="dropdown-header">D3</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">D3 3.0.4</a></li>

                                <li role="presentation" class="dropdown-header">CreateJS</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">CreateJS 2013.09.25</a></li>

                                <li role="presentation" class="dropdown-header">WebApp Install</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">WebApp Install 0.1</a></li>

                                <li role="presentation" class="dropdown-header">Thorax</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Thorax 2.0.0rc6</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Thorax 2.0.0rc3</a></li>

                                <li role="presentation" class="dropdown-header">Paper.js</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Paper.js 0.22</a></li>

                                <li role="presentation" class="dropdown-header">React</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">React 0.9.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">React 0.8.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">React 0.4.0</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">React 0.3.2</a></li>

                                <li role="presentation" class="dropdown-header">svg.js</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">svg.js 0.x (latest)</a></li>

                                <li role="presentation" class="dropdown-header">Minified</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Minified 1.0 beta1</a></li>

                                <li role="presentation" class="dropdown-header">jTypes</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">jTypes 2.1.0</a></li>

                                <li role="presentation" class="dropdown-header">Lo-Dash</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Lo-Dash 2.2.1</a></li>

                                <li role="presentation" class="dropdown-header">Brick</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Brick edge</a></li>

                                <li role="presentation" class="dropdown-header">RactiveJS</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">RactiveJS Latest</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">RactiveJS Edge</a></li>

                                <li role="presentation" class="dropdown-header">Vue</li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Vue (edge)</a></li>

                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">Vue 0.11.0</a></li>
                            </ul>
                            </div>
                        </div>
                        <br>
                        <div class="dropdown">
                            <div class="btn-group">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-expanded="true">
                                onLoad
                            <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu scrollable-menu" id="dropdownMenu2" role="menu" aria-labelledby="dropdownMenu2">
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">onLoad</a></li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">onDomReady</a></li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">No wrap - in head</a></li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">No wrap - in body</a></li>
                            </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Tinker Options -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                            Tinker Options
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                        <input type="text" placeholder="Name your Tinker">
                        <textarea placeholder="Description"></textarea>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"></input><span class="chk_lbl">Normalize css</span>
                            </label>
                        </div>
                        <p>Body tag</p>
                        <input type="text">
                        <p>DTD</p>
                        <div class="dropdown">
                            <div class="btn-group">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu3" data-toggle="dropdown" aria-expanded="true">
                                HTML 5
                            <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu scrollable-menu" id="dropdownMenu3" role="menu" aria-labelledby="dropdownMenu3">
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">XHTML 1.0 Strict</a></li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">XHTML 1.0 Transitional</a></li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">HTML 5</a></li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">HTML 4.01 Strict</a></li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">HTML 4.01 Transitional</a></li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">HTML 4.01 Frameset</a></li>
                            </ul>
                            </div>
                        </div>
                        <p>Framework script attribute</p>
                        <input type="text" placeholder="ie. data-type=''">
                    </div>
                </div>
            </div>
            <!-- External Resources -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="true" aria-controls="collapseThree">
                            External Resources
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        <input type="text" class="ext_res" placeholder="JavaScript/CSS URI">
                        <button type="button" class="btn btn-default add">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        </button>
                    </div>
                </div>
            </div>
            <!-- Languages -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingFour">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="true" aria-controls="collapseFour">
                            Languages
                        </a>
                    </h4>
                </div>
                <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                    <div class="panel-body">
                        <div class="dropdown langcontainer">
                            <div class="btn-group">
                            <button class="btn btn-default dropdown-toggle langbutton" type="button" id="dropdownMenu4" data-toggle="dropdown" aria-expanded="true">
                                HTML
                            <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu scrollable-menu" id="dropdownMenu4" role="menu" aria-labelledby="dropdownMenu4">
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">HTML</a></li>
                            </ul>
                            </div>
                        </div>
                        <div class="dropdown langcontainer langright">
                            <div class="btn-group">
                            <button class="btn btn-default dropdown-toggle langbutton" type="button" id="dropdownMenu5" data-toggle="dropdown" aria-expanded="true">
                                CSS
                            <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu scrollable-menu" id="dropdownMenu5" role="menu" aria-labelledby="dropdownMenu5">
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">CSS</a></li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">SCSS</a></li>
                            </ul>
                            </div>
                        </div>
                        <div class="dropdown langcontainer">
                            <div class="btn-group">
                            <button class="btn btn-default dropdown-toggle langbutton" type="button" id="dropdownMenu6" data-toggle="dropdown" aria-expanded="true">
                                JavaScript
                            <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu scrollable-menu" id="dropdownMenu6" role="menu" aria-labelledby="dropdownMenu6">
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">JavaScript</a></li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">CoffeeScript</a></li>
                                <li role="presentation"><a role="menuitem" tabindex="-1" href="">JavaScript 1.7</a></li>
                            </ul>
                            </div>
                        </div>
                        <div class="dropdown langcontainer langright">
                            <div class="btn-group">
                            <button class="btn btn-default langbutton" type="button" id="dropdownMenu7" data-toggle="dropdown" aria-expanded="true">
                                Results
                            </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
             <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingSix">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="true" aria-controls="collapseSix">
                            Default attributes
                        </a>
                    </h4>
                </div>
                <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                    <div class="panel-body">
                        <p>Please use below default attributes in the email template within any div</p>
                        <p>@portalurl: This attribute will replace the value with the portal URL link which is created by you in campaign while sending the email.</p>
                        <p>@surveyurl: This attribute will replace the value with the survey URL link which is created by you in campaign while sending the email.</p>
                        <p>@clicker: Inserts the image into the link to track clicks</p>
                      
                        <p>hello, hi there, Hi there: This attribute will replace hello with the actual first name of the contact </p>
                        
                    </div>
                </div>
                
            </div> 
             <div class="panel panel-default">
	            <div class="panel-heading" role="tab" id="headingSix">
	                    <h4 class="panel-title">
	                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="true" aria-controls="collapseSix">
	                            Custom attributes
	                        </a>
	                    </h4>
	                </div>
	                <div id="collapseSeven" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
	                    <div class="panel-body">
	                        <p>Please define your custom attributes in the EmailSetting tab in your Campaign screen before you can use in this HTML email template within any div</p>
	                        <p>Select EmailSetting and then click more.. button</p>
	                        <p>Add email attributes with key value pair.</p>
	                        <p>Any custom key should be prfix with @yourkey within the div. Ex: if key="fisrtname" and value="Mark" 
	                        then within the div the key will be as @firstname.
	                        </p>
	                         <p>The above out of box keys also can be used as custom attribute. 
	                         You can define any name of these attributes with the actual URL of these links either in public or private domain which can be accessed by the internet. 
	                         You can add any number of links or image url in the EmailAttributes tab under 
	                         EmailSetting screen and use them here as custom attributes
	                        </p>
	                        
	                    </div>
	                </div>
	          </div>
            <!-- Ajax Requests
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingFive">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="true" aria-controls="collapseFive">
                            Ajax Requests
                        </a>
                    </h4>
                </div>
                <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                    <div class="panel-body">
                        Stuff
                    </div>
                </div>
            </div> -->
            <!-- Legal, Credits and Links -->
            <!--  
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingSix">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="true" aria-controls="collapseSix">
                            Legal, Credits and Links
                        </a>
                    </h4>
                </div>
                <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                    <div class="panel-body">
                        <p>Created and maintained by <a title="John's homepage" href="http://johncipponeri.github.io/">John Cipponeri</a>.</p>
                        <p>CSS Design by <a title="Gianni's Twitter" href="https://twitter.com/Hazulu">@Hazulu</a></p>
                        <p>Hosted by <a href="https://pages.github.com/">GitHub Pages</a>.</p>
                        <p>Special thanks to <a href="http://jsfiddle.net" target="_new">JSFiddle</a> as this is an open source clone of it.</p>
                        <p><strong>License</strong></p>
                        <p>All code belongs to the poster and no license is enforced.</p>
                        <p>EmailTinker or its author are not responsible or liable for any loss or damage of any kind during the usage of provided code.</p>
                        <p><strong>Links</strong></p>
                        For updates please follow my
                        <a href="http://twitter.com/johncipponeri/" target="_new">tweets</a>
                    </div>
                </div>
            </div> -->
        </div>
    </div><!-- /sidebar -->

    <div class="editors">
        <div class="top">
            <!-- HTML Editor -->
            <div class="editorContainer">
            	<div>HTML</div>
                <div class="editor" id="html-editor"></div>
            </div>
            <!-- CSS EDitor -->
            <div class="editorContainer">
           	    <div>CSS</div>
                <div class="editor" id="css-editor"></div>
            </div>
        </div>
        <div class="bottom">
            <!-- JS Editor -->
            <div class="editorContainer">
                <div>JS</div>
                <div class="editor" id="js-editor"></div>
            </div>
            <!-- Preview -->
            <div class="editorContainer">
                 <div>RESULT</div>
                <iframe class="editor" id="preview" name="result" sandbox="allow-forms allow-popups allow-scripts allow-same-origin" frameborder="0">
                    #document
                </iframe>
            </div>
        </div>
    </div>

    </div>
    <form id="hidden_form">
    	<input id="body" type="hidden">
    	<input id="codetype" type="hidden">
    </form>
    <!-- Set Editor Modes -->
    <script>
   	    var www_url="<%=request.getParameter("baseurl") %>";
   	 	var token="<%=request.getParameter("token") %>";
   	 	var templateid="<%=request.getParameter("templateid") %>";
   	    var username="<%=request.getParameter("username") %>";
        ace.require("ace/ext/language_tools");

        var htmlEditor = ace.edit("html-editor");
        htmlEditor.setTheme("ace/theme/crimson-editor");
        htmlEditor.getSession().setMode("ace/mode/html");
        htmlEditor.setOptions({
            enableBasicAutocompletion: true
        });

        var cssEditor = ace.edit("css-editor");
        cssEditor.setTheme("ace/theme/crimson-editor");
        cssEditor.getSession().setMode("ace/mode/css");
        cssEditor.setOptions({
            enableBasicAutocompletion: true
        });

        var jsEditor = ace.edit("js-editor");
        jsEditor.setTheme("ace/theme/crimson-editor");
        jsEditor.getSession().setMode("ace/mode/javascript");
        jsEditor.setOptions({
            enableBasicAutocompletion: true
        });
        refresh();
    </script>
    
</body>
</html>
