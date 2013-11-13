package org.kozak127.phantom.InEventObject



import org.junit.*
import grails.test.mixin.*

@TestFor(InEventObjectController)
@Mock(InEventObject)
class InEventObjectControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/inEventObject/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.inEventObjectInstanceList.size() == 0
        assert model.inEventObjectInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.inEventObjectInstance != null
    }

    void testSave() {
        controller.save()

        assert model.inEventObjectInstance != null
        assert view == '/inEventObject/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/inEventObject/show/1'
        assert controller.flash.message != null
        assert InEventObject.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/inEventObject/list'

        populateValidParams(params)
        def inEventObject = new InEventObject(params)

        assert inEventObject.save() != null

        params.id = inEventObject.id

        def model = controller.show()

        assert model.inEventObjectInstance == inEventObject
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/inEventObject/list'

        populateValidParams(params)
        def inEventObject = new InEventObject(params)

        assert inEventObject.save() != null

        params.id = inEventObject.id

        def model = controller.edit()

        assert model.inEventObjectInstance == inEventObject
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/inEventObject/list'

        response.reset()

        populateValidParams(params)
        def inEventObject = new InEventObject(params)

        assert inEventObject.save() != null

        // test invalid parameters in update
        params.id = inEventObject.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/inEventObject/edit"
        assert model.inEventObjectInstance != null

        inEventObject.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/inEventObject/show/$inEventObject.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        inEventObject.clearErrors()

        populateValidParams(params)
        params.id = inEventObject.id
        params.version = -1
        controller.update()

        assert view == "/inEventObject/edit"
        assert model.inEventObjectInstance != null
        assert model.inEventObjectInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/inEventObject/list'

        response.reset()

        populateValidParams(params)
        def inEventObject = new InEventObject(params)

        assert inEventObject.save() != null
        assert InEventObject.count() == 1

        params.id = inEventObject.id

        controller.delete()

        assert InEventObject.count() == 0
        assert InEventObject.get(inEventObject.id) == null
        assert response.redirectedUrl == '/inEventObject/list'
    }
}
