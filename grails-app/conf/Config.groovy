import org.apache.log4j.DailyRollingFileAppender

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

log4j.main = {
	debug 'com.acn.mydata'
	error	'org.codehaus.groovy.grails.web.servlet',  //  controllers
			'org.codehaus.groovy.grails.web.pages', //  GSP
			'org.codehaus.groovy.grails.web.sitemesh', //  layouts
			'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
			'org.codehaus.groovy.grails.web.mapping', // URL mapping
			'org.codehaus.groovy.grails.commons', // core / classloading
			'org.codehaus.groovy.grails.plugins', // plugins
			'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
			'org.springframework',
			'org.hibernate',
			'net.sf.ehcache.hibernate',
			'grails.app'
}


// Log4j config from: http://blog.andresteingress.com/2012/03/22/grails-adding-more-than-one-log4j-configurations/
environments {
	development {
		log4j.env = {
			// environment-specific config
			def logDirectory = "log"

			appenders {
				appender new DailyRollingFileAppender(
					name: 'dailyAppender',
					datePattern: "'.'yyyy-MM-dd",  // See the API for all patterns.
					fileName: "${logDirectory}${System.getProperty('file.separator')}MyDataLite.log",
					layout: pattern(conversionPattern:'%d [%t] %-5p %c{2} %x - %m%n')
				)
			}
				
			root {
				error 'dailyAppender'
			}
		}
	}
	test {
		log4j.env = {
			// environment-specific config
			def logDirectory = "log"

			appenders {
				file name:'file', file:"${logDirectory}${System.getProperty("file.separator")}MyDataLite.log", maxFileSize:"100MB", maxBackupIndex: 10, layout: pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss,SSS} %p %c{2} %m%n')
				console name:'stdout'
			}
			root {
				error 'file', stdout
				additivity = true
			}
		}
	}
	production {
	  log4j.env = {
		  // environment-specific config
		  def logDirectory = System.getenv("CATALINA_HOME") + System.getProperty("file.separator") + "logs"
			
		  appenders {
			  appender new DailyRollingFileAppender(
				  name: 'dailyAppender',
				  datePattern: "'.'yyyy-MM-dd",  // See the API for all patterns.
				  fileName: "${logDirectory}${System.getProperty('file.separator')}MyDataLite.log",
				  layout: pattern(conversionPattern:'%d [%t] %-5p %c{2} %x - %m%n')
			  )
		  }
			  
		  root {
			  info 'dailyAppender'
		  }

	  }
	}
}



// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'com.acn.mydata.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'com.acn.mydata.UserRole'
grails.plugins.springsecurity.authority.className = 'com.acn.mydata.Role'

grails.plugins.springsecurity.securityConfigType = 'InterceptUrlMap'
//grails.plugins.springsecurity.rejectIfNoRule = true

grails.plugins.springsecurity.interceptUrlMap = [
	// Order matters here. Plugin picks up first map it matches
	'/js/**':        ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/css/**':       ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/images/**':    ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/login/**':     ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/logout/**':    ['IS_AUTHENTICATED_ANONYMOUSLY'],
	'/schema/layout/**':        ['IS_AUTHENTICATED_ANONYMOUSLY'],
	// Secure the rest of the application
	'/**':            ['ROLE_ADMIN']
]

// Admin users
admin.users.list = ['sogden','czinner','jlarche','rnahas']

