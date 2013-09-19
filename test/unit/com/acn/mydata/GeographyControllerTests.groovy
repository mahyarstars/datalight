package com.acn.mydata



import org.junit.*
import grails.test.mixin.*

@TestFor(GeographyController)
@Mock(Geography)
class GeographyControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/geography/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.geographyInstanceList.size() == 0
        assert model.geographyInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.geographyInstance != null
    }

    void testSave() {
        controller.save()

        assert model.geographyInstance != null
        assert view == '/geography/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/geography/show/1'
        assert controller.flash.message != null
        assert Geography.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/geography/list'

        populateValidParams(params)
        def geography = new Geography(params)

        assert geography.save() != null

        params.id = geography.id

        def model = controller.show()

        assert model.geographyInstance == geography
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/geography/list'

        populateValidParams(params)
        def geography = new Geography(params)

        assert geography.save() != null

        params.id = geography.id

        def model = controller.edit()

        assert model.geographyInstance == geography
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/geography/list'

        response.reset()

        populateValidParams(params)
        def geography = new Geography(params)

        assert geography.save() != null

        // test invalid parameters in update
        params.id = geography.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/geography/edit"
        assert model.geographyInstance != null

        geography.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/geography/show/$geography.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        geography.clearErrors()

        populateValidParams(params)
        params.id = geography.id
        params.version = -1
        controller.update()

        assert view == "/geography/edit"
        assert model.geographyInstance != null
        assert model.geographyInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/geography/list'

        response.reset()

        populateValidParams(params)
        def geography = new Geography(params)

        assert geography.save() != null
        assert Geography.count() == 1

        params.id = geography.id

        controller.delete()

        assert Geography.count() == 0
        assert Geography.get(geography.id) == null
        assert response.redirectedUrl == '/geography/list'
    }
}
