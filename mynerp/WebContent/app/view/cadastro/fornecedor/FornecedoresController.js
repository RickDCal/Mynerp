Ext.define('Mynerp.view.cadastro.fornecedor.FornecedoresController', {
    extend: 'Mynerp.view.base.ViewController',

    alias: 'controller.fornecedores',

    requires: [
        'Mynerp.view.cadastro.fornecedor.FornecedorWindow',
        'Mynerp.view.base.ViewController'
        //'Mynerp.ux.grid.Printer'
    ],

    createDialog: function(record){

        var me = this,
            view = me.getView(),
            glyphs = Mynerp.util.Glyphs;

        me.isEdit = !!record;
        me.dialog = view.add({
            xtype: 'fornecedor-window',
            viewModel: {
                data: {
                    title: record ? 'Editar: ' + record.get('id') + ' - ' + record.get('nome') : 'Cadastrar',
                    glyph: record ? glyphs.getGlyph('edit') : glyphs.getGlyph('add')
                },
                links: {
                    currentCadastro: record || {
                        type: 'Fornecedor',
                        create: true
                        // create: {
                        //     id: '0'
                        // }
                    }
                }
            },
            session: true //child session
        });

        me.dialog.show();

    },

    onSave: function(button, e, options){

        var me = this,
            dialog = me.dialog,
            view = me.getView(),
            form = me.lookupReference('fornecedorForm'),
            isEdit = me.isEdit,
            session = me.getSession(),
            store = me.getStore('fornecedores'),
            id;

        if (form.isValid()) {
            if (!isEdit) {
                id = dialog.getViewModel().get('currentCadastro').id;
            }
            dialog.getSession().save(); //salvou as alterações da tela na sessão e no servidor, a store ainda não sabe
            if (!isEdit) {
                //me.getStore('fornecedores').add(session.getRecord('Cliente', id)); // aqui adiciona o registro salvo da store pra não precisar buscar denovo com o sync
                store.add(session.getRecord('Fornecedor', id)); // aqui adiciona o registro salvo da store pra não precisar buscar denovo com o sync
            }

            store.sync({
                success: function(form, action) {
                    me.onCancel(); //fecha a janelinha
                    me.refresh(); //chama outro método logo abaixo
                    Mynerp.util.Util.showToast(form.proxy.getReader().rawData.mensagemRetorno);
                    //Mynerp.util.Util.showToast('Cadastro salvo com sucesso!'); // mostar mensagem
                },
                failure: function(form, action) {
                    Mynerp.util.Util.handleFormFailure(action);
                }
            });

        }

    },

    onFornecedorSelect: function(id){
        var me = this,
            grid = me.lookupReference('fornecedoresGrid'),
            store = me.getStore('fornecedores'),
            record = store.findRecord( 'id', id );

        if (record){
            grid.getSelectionModel().select(record);
        }
    },

    onItemClick: function( view, record, item, index, e, eOpts ) {
        this.redirectTo('fornecedores/' + record.get('id'));
    },    

    onDbClick: function( grid, record, tr, rowIndex, e, eOpts ) { // eu que coloquei este evento    
        var me = this;
        me.createDialog(record);
    },

    getRecordSelected: function(button) {
        var grid  = button.up('fornecedores-grid');
            return grid.getSelection();
    },   

    refresh: function(button, e, options) {
        var me = this,
            store = me.getStore('fornecedores');
        store.load();
    },

    onChangeTipoPessoa: function(field, newValue, oldValue, eOpts) {
        var me = this,
            form = me.lookupReference('fornecedorForm'),
            cnpj = me.lookupReference('cnpjFornecedor');
            nome = me.lookupReference('nomeFornecedor');
            sexo = me.lookupReference('sexoFornecedor');
            nascimento = me.lookupReference('nascimentoFornecedor');
            fantasia = me.lookupReference('fantasiaFornecedor');

            cnpj.reset();

            if (newValue == 1) {
                cnpj.setFieldLabel('CPF');
                nome.setFieldLabel('Nome');
                sexo.setHidden(false);
                nascimento.setHidden(false);
                fantasia.setHidden(true);
            } else if (newValue == 2) {
                cnpj.setFieldLabel('CNPJ');
                nome.setFieldLabel('Razão Social');
                sexo.setHidden(true);
                nascimento.setHidden(true);
                fantasia.setHidden(false);
            }
        
    },

    formataCpfCnpj: function(field, e, options) {
        var me = this, 
            tipoPessoa = me.lookupReference('tipoPessoa').getValue(),
            input = field.getValue(),
            tipo;

            Mynerp.util.Util.formataCpfCnpj(field, e, tipoPessoa);            
    }

});
