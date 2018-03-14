Ext.define('Mynerp.view.cadastro.fornecedor.FornecedoresModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.fornecedores',

    stores: {
        fornecedores: {
            alias: 'store.fornecedores',
            model: 'Mynerp.model.cadastro.Fornecedor',
            pageSize: 25,
            autoLoad: true,
            session: true
        }        
    }
});