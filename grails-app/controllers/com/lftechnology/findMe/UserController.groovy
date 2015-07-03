package com.lftechnology.findMe


import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(["IS_AUTHENTICATED_FULLY"])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model: [userInstanceCount: User.count()]
    }

    @Secured(["IS_AUTHENTICATED_FULLY"])
    def show(User userInstance) {
        respond userInstance
    }

    @Secured(["IS_AUTHENTICATED_FULLY"])
    def create() {
        respond new User(params)
    }

    @Secured(["IS_AUTHENTICATED_FULLY"])
    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view: 'create'
            return
        }

        userInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [
                    message(code: 'user.label', default: 'User'),
                    userInstance.id
                ])
                redirect userInstance
            }
            '*' { respond userInstance, [status: CREATED] }
        }
    }

    @Secured(["IS_AUTHENTICATED_FULLY"])
    def edit(User userInstance) {
        respond userInstance
    }

    @Secured(["IS_AUTHENTICATED_FULLY"])
    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view: 'edit'
            return
        }

        userInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [
                    message(code: 'User.label', default: 'User'),
                    userInstance.id
                ])
                redirect userInstance
            }
            '*' { respond userInstance, [status: OK] }
        }
    }

    @Secured(["IS_AUTHENTICATED_FULLY"])
    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [
                    message(code: 'User.label', default: 'User'),
                    userInstance.id
                ])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [
                    message(code: 'user.label', default: 'User'),
                    params.id
                ])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
