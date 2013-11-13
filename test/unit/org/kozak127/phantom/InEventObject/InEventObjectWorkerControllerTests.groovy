package org.kozak127.phantom.InEventObject



import org.junit.*
import grails.test.mixin.*

@TestFor(InEventObjectWorkerController)
@Mock(InEventObjectWorker)
class InEventObjectWorkerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/inEventObjectWorker/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.inEventObjectWorkerInstanceList.size() == 0
        assert model.inEventObjectWorkerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.inEventObjectWorkerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.inEventObjectWorkerInstance != null
        assert view == '/inEventObjectWorker/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/inEventObjectWorker/show/1'
        assert controller.flash.message != null
        assert InEventObjectWorker.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/inEventObjectWorker/list'

        populateValidParams(params)
        def inEventObjectWorker = new InEventObjectWorker(params)

        assert inEventObjectWorker.save() != null

        params.id = inEventObjectWorker.id

        def model = controller.show()

        assert model.inEventObjectWorkerInstance == inEventObjectWorker
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/inEventObjectWorker/list'

        populateValidParams(params)
        def inEventObjectWorker = new InEventObjectWorker(params)

        assert inEventObjectWorker.save() != null

        params.id = inEventObjectWorker.id

        def model = controller.edit()

        assert model.inEventObjectWorkerInstance == inEventObjectWorker
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/inEventObjectWorker/list'

        response.reset()

        populateValidParams(params)
        def inEventObjectWorker = new InEventObjectWorker(params)

        assert inEventObjectWorker.save() != null

        // test invalid parameters in update
        params.id = inEventObjectWorker.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/inEventObjectWorker/edit"
        assert model.inEventObjectWorkerInstance != null

        inEventObjectWorker.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/inEventObjectWorker/show/$inEventObjectWorker.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        inEventObjectWorker.clearErrors()

        populateValidParams(params)
        params.id = inEventObjectWorker.id
        params.version = -1
        controller.update()

        assert view == "/inEventObjectWorker/edit"
        assert model.inEventObjectWorkerInstance != null
        assert model.inEventObjectWorkerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/inEventObjectWorker/list'

        response.reset()

        populateValidParams(params)
        def inEventObjectWorker = new InEventObjectWorker(params)

        assert inEventObjectWorker.save() != null
        assert InEventObjectWorker.count() == 1

        params.id = inEventObjectWorker.id

        controller.delete()

        assert InEventObjectWorker.count() == 0
        assert InEventObjectWorker.get(inEventObjectWorker.id) == null
        assert response.redirectedUrl == '/inEventObjectWorker/list'
    }
}
