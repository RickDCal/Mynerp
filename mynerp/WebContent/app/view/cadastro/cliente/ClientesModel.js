Ext.define('Mynerp.view.cadastro.cliente.ClientesModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.clientes',

    stores: {
        clientes: {
            //type: 'buscar-clientes',
            alias: 'store.clientes',
            model: 'Mynerp.model.cadastro.Cliente',
            pageSize: 25,
            autoLoad: true,
            session: true
        }        
    }//,

    // formulas: {
    //     specialFeatures : {

    //         bind: {
    //             bindTo: '{currentFilm.special_features}',
    //             deep: true
    //         },

    //         get: function(value){
    //             var values = value ? value.split(',') : [],
    //                 texts = [];
    //             values.forEach(function(item){
    //                 texts.push(Ext.create('Mynerp.model.TextCombo',{
    //                     text: item
    //                 }));
    //             });
    //             return texts;
    //         },

    //         set: function(value){
    //             if (value){
    //                 this.get('currentFilm').set('special_features', value.join());
    //             }
    //         }
    //     }
    // }
});