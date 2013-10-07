package org.kozak127.phantom



import org.junit.*
import grails.test.mixin.*

@TestFor(StallWorkerController)
@Mock(StallWorker)
class StallWorkerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/stallWorker/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.stallWorkerInstanceList.size() == 0
        assert model.stallWorkerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.stallWorkerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.stallWorkerInstance != null
        assert view == '/stallWorker/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/stallWorker/show/1'
        assert controller.flash.message != null
        assert StallWorker.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/stallWorker/list'

        populateValidParams(params)
        def stallWorker = new StallWorker(params)

        assert stallWorker.save() != null

        params.id = stallWorker.id

        def model = controller.show()

        assert model.stallWorkerInstance == stallWorker
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/stallWorker/list'

        populateValidParams(params)
        def stallWorker = new StallWorker(params)

        assert stallWorker.save() != null

        params.id = stallWorker.id

        def model = controller.edit()

        assert model.stallWorkerInstance == stallWorker
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/stallWorker/list'

        response.reset()

        populateValidParams(params)
        def stallWorker = new StallWorker(params)

        assert stallWorker.save() != null

        // test invalid parameters in update
        params.id = stallWorker.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/stallWorker/edit"
        assert model.stallWorkerInstance != null

        stallWorker.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/stallWorker/show/$stallWorker.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        stallWorker.clearErrors()

        populateValidParams(params)
        params.id = stallWorker.id
        params.version = -1
        controller.update()

        assert view == "/stallWorker/edit"
        assert model.stallWorkerInstance != null
        assert model.stallWorkerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/stallWorker/list'

        response.reset()

        populateValidParams(params)
        def stallWorker = new StallWorker(params)

        assert stallWorker.save() != null
        assert StallWorker.count() == 1

        params.id = stallWorker.id

        controller.delete()

        assert StallWorker.count() == 0
        assert StallWorker.get(stallWorker.id) == null
        assert response.redirectedUrl == '/stallWorker/list'
    }
}
