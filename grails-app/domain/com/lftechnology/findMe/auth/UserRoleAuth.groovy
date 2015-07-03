package com.lftechnology.findMe.auth

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class UserRoleAuth implements Serializable {

	private static final long serialVersionUID = 1

	UserRole userRole
	Auth auth

	UserRoleAuth(UserRole u, Auth r) {
		this()
		userRole = u
		auth = r
	}

	@Override
	boolean equals(other) {
		if (!(other instanceof UserRoleAuth)) {
			return false
		}

		other.userRole?.id == userRole?.id && other.auth?.id == auth?.id
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (userRole) builder.append(userRole.id)
		if (auth) builder.append(auth.id)
		builder.toHashCode()
	}

	static UserRoleAuth get(long userRoleId, long authId) {
		criteriaFor(userRoleId, authId).get()
	}

	static boolean exists(long userRoleId, long authId) {
		criteriaFor(userRoleId, authId).count()
	}

	private static DetachedCriteria criteriaFor(long userRoleId, long authId) {
		UserRoleAuth.where {
			userRole == UserRole.load(userRoleId) &&
			auth == Auth.load(authId)
		}
	}

	static UserRoleAuth create(UserRole userRole, Auth auth, boolean flush = false) {
		def instance = new UserRoleAuth(userRole, auth)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(UserRole u, Auth r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = UserRoleAuth.where { userRole == u && auth == r }.deleteAll()

		if (flush) { UserRoleAuth.withSession { it.flush() } }

		rowCount
	}

	static void removeAll(UserRole u, boolean flush = false) {
		if (u == null) return

		UserRoleAuth.where { userRole == u }.deleteAll()

		if (flush) { UserRoleAuth.withSession { it.flush() } }
	}

	static void removeAll(Auth r, boolean flush = false) {
		if (r == null) return

		UserRoleAuth.where { auth == r }.deleteAll()

		if (flush) { UserRoleAuth.withSession { it.flush() } }
	}

	static constraints = {
		auth validator: { Auth r, UserRoleAuth ur ->
			if (ur.userRole == null || ur.userRole.id == null) return
			boolean existing = false
			UserRoleAuth.withNewSession {
				existing = UserRoleAuth.exists(ur.userRole.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['userRole', 'auth']
		version false
	}
}
