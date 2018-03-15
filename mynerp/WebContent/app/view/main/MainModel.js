/**
 * This class is the view model for the Main view of the application.
 */
Ext.define('Mynerp.view.main.MainModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.main',

    data: {
        name: 'Mynerp',
        appName: 'Transcal Transportes',
        appHeaderIcon: '<span class = "fa fa-truck fa-lg app-header-logo">',
        footer: 'Mynerp V2.0.0'
    }

    //TODO - add data, formulas and/or methods to support your view
});