Ext.define('Mynerp.view.listaSelecao.cliente.ListaClientesModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.listaclientes',

    stores: {
        clientes: {
            //type: 'buscar-clientes',
            alias: 'store.clientes',
            model: 'Mynerp.model.cadastro.Cliente',
            pageSize: 20,
            autoLoad: true,
            session: false // verificar isso
        }        
    }
});