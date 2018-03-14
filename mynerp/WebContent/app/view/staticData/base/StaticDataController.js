Ext.define('Mynerp.view.staticData.base.staticDataController', {
    extend: 'Mynerp.view.base.ViewController',

    // Esta classe é bem semelhante à do exemplo no kitchensink "isolated child session" no controller

    alias: 'controller.staticData',

    requires: [
        //'Mynerp.view.cadastro.cliente.ClienteWindow',
        'Mynerp.view.base.ViewController'
        //'Mynerp.ux.grid.Printer'
    ],

    onAdd: function (button, e, options) {
        this.createDialog(null, button);
    },

    createDialog: function(record, button){

        var me = this,
            view = me.getView(),
            glyphs = Mynerp.util.Glyphs,
            caller, xtypeJanela, titulo;

            caller = button.up('grid').xtype;

        switch (caller) {
            case 'cobrancas-grid' : 
                xtypeJanela = 'cobranca-window';
                me.tipoObjeto = 'Cobranca';
                titulo = 'Forma de Cobranca'
            break;
            case 'condicoes-pagamento-grid' : 
                xtypeJanela = 'condicao-pagamento-window';
                me.tipoObjeto = 'CondicaoPagamento';
                titulo = 'Condição de Pagamento';
                model =  Mynerp.model.staticData.financeiro.CondicaoPagamento;
            break;
            default:  Mynerp.util.Util.showErrorMsg ('Não foi possível determinar o tipo da janela a ser exibida.');
            break;
        }

        me.isEdit = !!record;
        me.dialog = view.add({
            xtype:  xtypeJanela,
            viewModel: {
                data: {
                    title: record ? 'Editar ' + titulo + ': ' + record.get('id') + ' - ' + record.get('nome') : 'Cadastrar ' + titulo,
                    glyph: record ? glyphs.getGlyph('edit') : glyphs.getGlyph('add')
                },
                links: {
                    currentCadastro: record || {
                        type:  me.tipoObjeto,
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

     onEdit: function (button) { // override no onEdit da base.ViewController
        var me = this;
        try {
            me.createDialog(button.getWidgetRecord(), button); // se o botão estiver na linha do grid
        } catch (e) {// se o botão estiver na barra
            records = me.getRecordSelected(button);
            record = records[0];
            if (!!record) {
                me.createDialog(record, button);
            } else {
                Mynerp.util.Util.showAlertMsg('Nenhum registro selecionado para edição.');
            } 
        }                

    },    

    onSave: function(button, e, options){

        var me = this,
            dialog = me.dialog,
            view = me.getView(),
            isEdit = me.isEdit,
            session = me.getSession(),
            grid = button.up('grid'),
            store = grid.getStore(),
            id, form, rec;

            switch (me.tipoObjeto) {
                case 'Cobranca': form = me.lookupReference('cobrancaForm');
                break;
                case 'CondicaoPagamento': form = me.lookupReference('condicaoPagamentoForm');
                break;
                default: console.log('Tipo de objeto não informado');
                break;
            }

        if (form.isValid()) {

            rec = dialog.getViewModel().get('currentCadastro');
            dialog.getSession().save();
            rec.save({
                failure: function(record, operation) {
                    Mynerp.util.Util.showErrorMsg(operation.getProxy().reader.rawData.mensagemRetorno);
                },
                success: function(record, operation) {
                    me.onCancel(); //fecha a janelinha
                    me.refresh(button); //chama outro método logo abaixo
                    Mynerp.util.Util.showToast(operation.getProxy().reader.rawData.mensagemRetorno);
                },
                callback: function(record, operation, success) {
                    // do something whether the save succeeded or failed
                }
            });






            // if (!isEdit) {
            //     id = dialog.getViewModel().get('currentCadastro').id;
            // }
            // dialog.getSession().save(); //salvou as alterações da tela na sessão e no servidor, a store ainda não sabe
            // if (!isEdit) {
            //     store.add(session.getRecord(me.tipoObjeto, id)); // aqui adiciona o registro salvo da store pra não precisar buscar denovo com o sync
            // }
            
            // store.sync({
            //     success: function(form, action) {
            //         me.onCancel(); //fecha a janelinha
            //         me.refresh(button); //chama outro método logo abaixo
            //         Mynerp.util.Util.showToast(form.proxy.getReader().rawData.mensagemRetorno);
            //     },
            //     failure: function(form, action) {
            //         Mynerp.util.Util.handleFormFailure(action);
            //     }
            // });


        }

    },

    onRecordSelect: function(id){
        var me = this,
            view = me.getView(),
            grid = view.down('grid'),
            store = grid.getStore();
            record = store.findRecord( 'id', id );

        if (record){
            grid.getSelectionModel().select(record);
        }
    },

    onDbClick: function( grid, record, tr, rowIndex, e, eOpts ) { // eu que coloquei este evento    
        var me = this,
        button = me.lookupReference('editGridButton');
        me.createDialog(record, button);
    },

    getRecordSelected: function(button) {
        var grid  = button.up('grid');
            return grid.getSelection();
    },

    refresh: function(button, e, options) {
        var me = this,
        view = me.getView(),
        grid = view.down('grid'),
        store = grid.getStore();
        store.load();
    }    

});
