package com.lftechnology.findMe


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RoomController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Room.list(params), model: [roomInstanceCount: Room.count()]
    }

    def show(Room roomInstance) {
        respond roomInstance
    }

    def create() {
        respond new Room(params)
    }

    @Transactional
    def save(Room roomInstance) {
        if (roomInstance == null) {
            notFound()
            return
        }

        if (roomInstance.hasErrors()) {
            respond roomInstance.errors, view: 'create'
            return
        }

        roomInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'room.label', default: 'Room'), roomInstance.id])
                redirect roomInstance
            }
            '*' { respond roomInstance, [status: CREATED] }
        }
    }

    def edit(Room roomInstance) {
        respond roomInstance
    }

    @Transactional
    def update(Room roomInstance) {
        if (roomInstance == null) {
            notFound()
            return
        }

        if (roomInstance.hasErrors()) {
            respond roomInstance.errors, view: 'edit'
            return
        }

        roomInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Room.label', default: 'Room'), roomInstance.id])
                redirect roomInstance
            }
            '*' { respond roomInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Room roomInstance) {

        if (roomInstance == null) {
            notFound()
            return
        }

        roomInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Room.label', default: 'Room'), roomInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'room.label', default: 'Room'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
