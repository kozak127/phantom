package org.kozak127.phantom.InEventObject



import org.junit.*
import grails.test.mixin.*

@TestFor(InEventObjectStaffController)
@Mock(InEventObjectStaff)
class InEventObjectStaffControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/inEventObjectStaff/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.inEventObjectStaffInstanceList.size() == 0
        assert model.inEventObjectStaffInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.inEventObjectStaffInstance != null
    }

    void testSave() {
        controller.save()

        assert model.inEventObjectStaffInstance != null
        assert view == '/inEventObjectStaff/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/inEventObjectStaff/show/1'
        assert controller.flash.message != null
        assert InEventObjectStaff.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/inEventObjectStaff/list'

        populateValidParams(params)
        def inEventObjectStaff = new InEventObjectStaff(params)

        assert inEventObjectStaff.save() != null

        params.id = inEventObjectStaff.id

        def model = controller.show()

        assert model.inEventObjectStaffInstance == inEventObjectStaff
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/inEventObjectStaff/list'

        populateValidParams(params)
        def inEventObjectStaff = new InEventObjectStaff(params)

        assert inEventObjectStaff.save() != null

        params.id = inEventObjectStaff.id

        def model = controller.edit()

        assert model.inEventObjectStaffInstance == inEventObjectStaff
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/inEventObjectStaff/list'

        response.reset()

        populateValidParams(params)
        def inEventObjectStaff = new InEventObjectStaff(params)

        assert inEventObjectStaff.save() != null

        // test invalid parameters in update
        params.id = inEventObjectStaff.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/inEventObjectStaff/edit"
        assert model.inEventObjectStaffInstance != null

        inEventObjectStaff.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/inEventObjectStaff/show/$inEventObjectStaff.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        inEventObjectStaff.clearErrors()

        populateValidParams(params)
        params.id = inEventObjectStaff.id
        params.version = -1
        controller.update()

        assert view == "/inEventObjectStaff/edit"
        assert model.inEventObjectStaffInstance != null
        assert model.inEventObjectStaffInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/inEventObjectStaff/list'

        response.reset()

        populateValidParams(params)
        def inEventObjectStaff = new InEventObjectStaff(params)

        assert inEventObjectStaff.save() != null
        assert InEventObjectStaff.count() == 1

        params.id = inEventObjectStaff.id

        controller.delete()

        assert InEventObjectStaff.count() == 0
        assert InEventObjectStaff.get(inEventObjectStaff.id) == null
        assert response.redirectedUrl == '/inEventObjectStaff/list'
    }
}
