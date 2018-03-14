Ext.define('Mynerp.view.financeiro.contaReceber.ContaReceberController', {
    extend: 'Mynerp.view.base.ViewController',

    alias: 'controller.contasreceber',

    requires: [
        'Mynerp.view.financeiro.contaReceber.ContaReceberWindow'
    ],

    onSave: function(button, e, options){

        var me = this,
            dialog = me.dialog,
            view = me.getView(),
            form = me.lookupReference('contaReceberForm'),
            grid = me.lookupReference('gridParcelasCR'),
            isEdit = me.isEdit,
            session = me.getSession(),
            associatedStore = grid.getStore(),
            //store = me.getStore('parcelasreceber'),
            store = me.getStore('contasreceber'),
            model =  Mynerp.model.financeiro.ContaReceber,
            rec, nome, id, codigoConta;

            if (form.isValid()) {
                dialog.getViewModel().get('currentCadastro').set('dataAlteracao', new Date());  
            
            if (isEdit) {
                dialog.getSession().save();
            } else {
                         
                rec = dialog.getViewModel().get('currentCadastro');
                nome = rec.get('nomePessoa');
                rec.setSession(null);
                rec._cliente = null; 
                store.add(rec); // aqui adiciona o registro salvo da store pra não precisar buscar denovo com o sync
            }

            //me.viewSessionChanges();

            var batch = session.getSaveBatch();
            
            if (batch){
                for (i=0; i < me.parcelasRemover.length; i++) {
                    me.parcelasRemover[i].erase();
                }
                //var batchCallback = batch.start(); // start dispara as atualizações e retorna um batch tbem
                //batchCallback.on("complete", function ( batch, operation, eOpts) {
                batch.start(); // start dispara as atualizações e retorna um batch tbem
                batch.on("complete", function ( batch, operation, eOpts) {
                   try {
                        if (!me.idObjeto) {
                            me.idObjeto = batch.operations[0].getResultSet().getRecords()[0].id; 
                        }
                        codigoConta = me.idObjeto;
                        Mynerp.util.Util.showToast('Conta Nº '+ codigoConta + ' adicionada com sucesso!');
                        me.createDialog(model.load(codigoConta)); // abre a janelinha denovo com o registro novo 
                        me.lookupReference('gridParcelasCR').sort("sequencial", "ASC");
                        //me.getStore('contasreceber').sync();
                   } catch (e) {
                        Mynerp.util.Util.showToast('Registro atualizado!');
                   }      
                });
                //associatedStore.sync();
            }
            
            /* v1.0 com prolemas de aparecer uma linha a mais nas parcelas após gravar -- retomar este funcionamento depois e consertar
            store.sync({ //isto funciona mas após a gravação as parcelas aparecem duplicadas....

                success: function(batch, options) {

                    // var response = batch.proxy.getReader().rawData.mensagemRetorno,
                    // newId = batch.operations[0].getResultSet().getRecords()[0].id; 

                    // me.refresh();
                    // me.onCancel(); //fecha a janelinha
                    
                    // newModel = model.load(newId);
                    // newModel.set('nomePessoa', nome);
                    // me.createDialog(newModel); // abre a janelinha denovo com o registro novo

                    ///////////////////

                    var response = batch.proxy.getReader().rawData.mensagemRetorno,
                    newModel = batch.operations[0].getResultSet().getRecords()[0]; 

                    me.refresh();
                    me.onCancel(); //fecha a janelinha
                    
                    me.createDialog(newModel); // abre a janelinha denovo com o registro novo

                    Mynerp.util.Util.showToast(response);

                    me.createDialog(model.load(newId)); // abre a janelinha denovo com o registro novo
                },
                failure: function(batch, options) {
                    // no model base já tem um listaner de exception que retorna a mensagem que vem do servidor.
                    //Mynerp.util.Util.handleStoreSyncFailure(batch, options);
                }
            });
            v1.0 */
            me.refresh();
            me.onCancel(); 
            
        }
    },

    onRemoveParcelaClick: function(grid, rowIndex){
        var me = this;
        console.log('passou');
        me.parcelasRemover.push(grid.getStore().getAt(rowIndex));
        //grid.getStore().getAt(rowIndex).erase();
        console.log(me.parcelasRemover);
        grid.getStore().removeAt(rowIndex);
        //grid.getStore().on("remove");
        //grid.getSelection()[0].erase();
        //grid.getStore().remove(grid.getSelection()[0]);
    },

    onContaSelect: function(id){
        var me = this,
            grid = me.lookupReference('parcelaReceberGrid'),
            store = me.getStore('parcelasreceber'),
            record = store.findRecord( 'id', id );

        if (record){
            grid.getSelectionModel().select(record);
        }
    },

    onItemClick: function( view, record, item, index, e, eOpts ) {
        this.redirectTo('contasreceber/' + record.get('id'));
    },

    // createDialog: function(record){

    //     var me = this,
    //         view = me.getView(),
    //         glyphs = Mynerp.util.Glyphs,
    //         model =  Mynerp.model.financeiro.ContaReceber,
    //         data = new Date(),
    //         entity, nome;

    //     if (!me.dialog) { //só faz alguma coisa se a janela ainda não existir. estava dando problema qdo clicava em adicionar qdo estava editando
    //         me.isEdit = !!record;     
        
    //         if (me.isEdit) {
    //             entity = record.entityName;
    //             nome = record.nomePessoa;
    //             if (entity == 'ParcelaReceber' || entity == 'ParcelaPagar') {                    
    //                 record = model.load(record.get('idConta'));  //o método load é assincrono
    //             } else {
    //                 data = record.dataEmissaoConta;
    //                 record = model.load(record.id, {
    //                     scope: this,
    //                     success: function(record, operation) {
    //                         //deixando isso aqui só para lembrar que o load é assincrono. até então só tenho do record aquilo que veio como parametro
    //                     },
    //                     failure: function(record, operation) {
    //                         //do something if the load failed
    //                     }
    //                 }); 
    //             }
    //             // nome = record.get('nomePessoa');
    //             // record = model.load(record.get('idConta')); //o método load é assincrono
    //         }

    //         me.dialog = view.add({
    //             xtype: 'conta-receber-window',
    //             viewModel: {
    //                 data: {
    //                      title: record ? 'Editar: ' + record.get('id') + ' - Cliente: ' + nome : 'Cadastrar',
    //                      glyph: record ? glyphs.getGlyph('edit') : glyphs.getGlyph('add')
    //                 },
    //                 links: {
    //                     currentCadastro: record || {
    //                         type: 'ContaReceber',
    //                         //create: true
    //                         create: {
    //                             dataEmissaoConta: data
    //                         }
    //                     }
    //                 }
    //             },
    //             session: true //child session
    //         });
    //         me.dialog.show();
    //     }         

    // },


    createDialog: function(record){

        var me = this,
            //view = me.getView(),            
            model =  Mynerp.model.financeiro.ContaReceber,
            data = new Date(),
            entity, nome, id;
            me.parcelasRemover = [];
        
        me.getStore('parametroFinanceiro').load(function(records, operation, success) {
            me.parametroFinanceiro = records[0];
        });

        if (!me.dialog) { //só faz alguma coisa se a janela ainda não existir. estava dando problema qdo clicava em adicionar qdo estava editando
            me.isEdit = !!record;     
        
            if (me.isEdit) {
                entity = record.entityName;
                if (entity == 'ParcelaReceber' || entity == 'ParcelaPagar') {  
                    id = record.get('idConta'); 
                    //nome = record.get('nomePessoa');                 
                     //o método load é assincrono
                } else {
                    data = record.dataEmissaoConta;
                    id = record.id; 
                    // record = model.load(record.get('id'), {
                    //     scope: this,
                    //     success: function(record, operation) {
                    //         //deixando isso aqui só para lembrar que o load é assincrono. até então só tenho do record aquilo que veio como parametro
                    //     },
                    //     failure: function(record, operation) {
                    //         //do something if the load failed
                    //     }
                    // }); 
                    //me.doDialog(record, nome);
                }

                record = model.load(id, {
                        scope: this,
                        success: function(record, operation) {
                            me.doDialog(record);
                        },
                        failure: function(record, operation) {
                            //do something if the load failed
                        }
                    });  //o método load é assincrono

                me.idObjeto = id;

                // nome = record.get('nomePessoa');
                // record = model.load(record.get('idConta')); //o método load é assincrono
            } else {
                me.doDialog(null);
            }

            // me.dialog = view.add({
            //     xtype: 'conta-receber-window',
            //     viewModel: {
            //         data: {
            //              title: record ? 'Editar: ' + record.get('id') + ' - Cliente: ' + nome : 'Cadastrar',
            //              glyph: record ? glyphs.getGlyph('edit') : glyphs.getGlyph('add')
            //         },
            //         links: {
            //             currentCadastro: record || {
            //                 type: 'ContaReceber',
            //                 //create: true
            //                 create: {
            //                     dataEmissaoConta: data
            //                 }
            //             }
            //         }
            //     },
            //     session: true //child session
            // });
            
            //me.dialog.show();
        }         

    },

    doDialog: function (record) {
        var me = this,
        view = me.getView(),
        glyphs = Mynerp.util.Glyphs;

        me.dialog = view.add({
                xtype: 'conta-receber-window',
                viewModel: {
                    data: {
                         title: record ? 'Editar: ' + record.get('id') + ' - Cliente: ' + record.get('nomePessoa') : 'Cadastrar',
                         glyph: record ? glyphs.getGlyph('edit') : glyphs.getGlyph('add'),
                         idObjeto: record ? record.get('id') : null
                    },
                    links: {
                        currentCadastro: record || {
                            type: 'ContaReceber',
                            //create: true
                            create: {
                                dataEmissaoConta: new Date()
                            }
                        }
                    }
                },
                session: true //child session
            });
        me.dialog.show();
    },

    onDbClick: function( grid, record, tr, rowIndex, e, eOpts ) { // eu que coloquei este evento    
        var me = this;
        me.createDialog(record);
    },

    getRecordSelected: function(button) {
        var grid  = button.up('parcela-receber-grid');
            return grid.getSelection();
    },  

    refresh: function(button, e, options) {
        var me = this,
            store = me.getStore('parcelasreceber');
            store2 = me.getStore('contasreceber');
        store.load();
        store2.load();

    },

    onAddParcelaClick: function(button, e, options){        
        var me = this,
            isEdit = me.isEdit,
            dialog = me.dialog,
            grid = me.lookupReference('gridParcelasCR'),
            store = grid.getStore(),
            seq = store.count() + 1 ,
            doc = dialog.getViewModel().get('currentCadastro').get('numeroDocumento'),
            dataAtual = new Date(),
            dataVenc = new Date(),
            docParcela;

            dataVenc.setDate(dataAtual.getDate() + (seq * 30));

            if (!isEdit) {
                store.setSession(null);
            } 

            if (doc) {
                docParcela = doc + '-' + seq
            } else {
                docParcela = null
            }

            

            record = new Mynerp.model.financeiro.ParcelaReceber({
                sequencial: seq,
                dataEmissao: dataAtual,
                numeroDocto: docParcela,
                dataVencimento: dataVenc,          
                valor: 0.00,
                idCobranca: me.parametroFinanceiro.get('idCobranca'),
                cobrancaGridParcela: me.parametroFinanceiro.get('nomeCobranca')

            });            

        //grid.getStore().insert(0, linha); este "insert" me permite escolher a posição pelo index
        store.add(record); // este insere sempre no final
        grid.getPlugin('cellplugin').startEditByPosition({
             row: 0,
             column: 0
         });
    },

    onGerarParcelaClick: function(grid, rowIndex){
        console.log('passou');
        this.removeResiduos();
        
    },

    removeResiduos: function(){  
    // por algum motivo se adicionar uma parcela em um grid de conta já existente, depois de salvar carrega a parcela duas vezes no grid.
    //não cnosegui descobrid o porque então estou tirando esta parcela duplicada depois que o grid é desenhado. 

        // var me = this,
        // gridParcela = me.lookupReference('gridParcelasCR'),
        // associatedStore = gridParcela.getStore(),
        // store = me.getStore('contasreceber');

        // associatedStore.each(function (record) {
        //     if(record.getId() < 0) {
        //         associatedStore.remove(record);
        //     }
        // }); 

        // store.each(function (record) {
        //     if (record.id < 0) {
        //         store.remove(record);
        //     }
        // });
        
    },

    onBatchComplete: function ( batch, operation, eOpts) {
       var me = this,
       codigoConta;

       try {
            codigoConta  = batch.operations[0].getResultSet().getRecords()[0].id; 
            Mynerp.util.Util.showToast('Conta Nº '+ codigoConta + ' adicionada com sucesso!');

       } catch (e) {
            Mynerp.util.Util.showToast('Registro atualizado!');
       } 
        return codigoConta;       
    }

});
