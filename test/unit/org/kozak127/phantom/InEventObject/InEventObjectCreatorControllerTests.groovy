package org.kozak127.phantom.InEventObject



import org.junit.*
import grails.test.mixin.*

@TestFor(InEventObjectCreatorController)
@Mock(InEventObjectCreator)
class InEventObjectCreatorControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/inEventObjectCreator/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.inEventObjectCreatorInstanceList.size() == 0
        assert model.inEventObjectCreatorInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.inEventObjectCreatorInstance != null
    }

    void testSave() {
        controller.save()

        assert model.inEventObjectCreatorInstance != null
        assert view == '/inEventObjectCreator/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/inEventObjectCreator/show/1'
        assert controller.flash.message != null
        assert InEventObjectCreator.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/inEventObjectCreator/list'

        populateValidParams(params)
        def inEventObjectCreator = new InEventObjectCreator(params)

        assert inEventObjectCreator.save() != null

        params.id = inEventObjectCreator.id

        def model = controller.show()

        assert model.inEventObjectCreatorInstance == inEventObjectCreator
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/inEventObjectCreator/list'

        populateValidParams(params)
        def inEventObjectCreator = new InEventObjectCreator(params)

        assert inEventObjectCreator.save() != null

        params.id = inEventObjectCreator.id

        def model = controller.edit()

        assert model.inEventObjectCreatorInstance == inEventObjectCreator
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/inEventObjectCreator/list'

        response.reset()

        populateValidParams(params)
        def inEventObjectCreator = new InEventObjectCreator(params)

        assert inEventObjectCreator.save() != null

        // test invalid parameters in update
        params.id = inEventObjectCreator.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/inEventObjectCreator/edit"
        assert model.inEventObjectCreatorInstance != null

        inEventObjectCreator.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/inEventObjectCreator/show/$inEventObjectCreator.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        inEventObjectCreator.clearErrors()

        populateValidParams(params)
        params.id = inEventObjectCreator.id
        params.version = -1
        controller.update()

        assert view == "/inEventObjectCreator/edit"
        assert model.inEventObjectCreatorInstance != null
        assert model.inEventObjectCreatorInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/inEventObjectCreator/list'

        response.reset()

        populateValidParams(params)
        def inEventObjectCreator = new InEventObjectCreator(params)

        assert inEventObjectCreator.save() != null
        assert InEventObjectCreator.count() == 1

        params.id = inEventObjectCreator.id

        controller.delete()

        assert InEventObjectCreator.count() == 0
        assert InEventObjectCreator.get(inEventObjectCreator.id) == null
        assert response.redirectedUrl == '/inEventObjectCreator/list'
    }
}
