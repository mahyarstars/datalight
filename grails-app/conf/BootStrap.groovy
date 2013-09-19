import com.acn.mydata.*
import grails.util.GrailsUtil

class BootStrap {
	def springSecurityService
	def grailsApplication
	
	def init = { servletContext ->
		
		// Admin role
		def adminRole = Role.findByAuthority('ROLE_ADMIN')
		
		if(!adminRole){
			adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		}

		// User role
		def userRole = Role.findByAuthority('ROLE_USER')
		
		if(!userRole){
			userRole = new Role(authority: 'ROLE_USER').save(flush: true)
		}
		
		// Anonymous role
		def anonymousRole = Role.findByAuthority('IS_AUTHENTICATED_ANONYMOUSLY')
		
		if(!anonymousRole){
			anonymousRole = new Role(authority: 'IS_AUTHENTICATED_ANONYMOUSLY').save(flush: true)
		}
		
		
		grailsApplication.config.admin.users.list.each { authUser ->
			// map primary admin user to admin role
			def adminUser = User.findByUsername(authUser)
			
			if (!adminUser) {
				log.info "Bootstrap creating user: ${authUser}..."
				
				// set a randomly generated password, which will be
				// stored as encrypted hash in the database
				def password = "c3nsus"
	
				adminUser = new User(username: authUser,
							fullname: authUser,
							enabled: true,
							password:  password)
	
				if (!adminUser.hasErrors() && adminUser.save(flush: true,validate:true)) {
					log.info "User ${authUser} saved"
				} else {
					log.error "Could not save ${authUser} user: ${ adminUser.errors}"
				}
	
				UserRole.create adminUser, adminRole, true
			}

		}
		
		// Create admin user
		String password = 'c3nsus'
		def adminUser = User.findByUsername("admin")
		
		if (!adminUser) {
			adminUser = new User(username: 'admin',
								fullname:"Administrator",
								enabled: false,
								password: password)
			
			if (!adminUser.hasErrors() && adminUser.save(flush: true, validate:true)   ) {
				log.info "User saved"
			} else {
				log.error "Could not save admin user: ${adminUser.errors}"
			}
			UserRole.create adminUser, adminRole, true
		}
		
	}

	
    def destroy = {
    }
}
