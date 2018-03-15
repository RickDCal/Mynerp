Ext.define('Mynerp.view.cadastro.base.FornecedorForm', {
	extend: 'Ext.form.Panel',
	xtype: 'fornecedor-form',

	//reference: 'fornecedorForm', // já foi passado na window, referente a este form que é item dela

	requires: [
   		'Mynerp.util.Util',
   		'Mynerp.util.Glyphs'
   ],

			bodyPadding: 10,
			//width: 800, // o layout da janela é fit então não precisa destas medidas qui no filho, pois ajanela já tem medida
			//height: 500,
			layout: {
			type: 'anchor'
			},

			modelValidation: true, 
			scrollable: true,

			defaults: { //será aplicado no próximo array de itens que ele encontrar
				xtype: 'fieldset',
				layout: 'anchor',
				defaults: {
				anchor: '100%'
				}
			},        

			items: [
				//identificação
				{
				xtype: 'fieldset',
				title: 'Identificação',

				items: [
					   {
					   xtype: 'container',
					   layout: 'hbox',
					   combineErrors: true,
					   defaults: {
						   flex: 1,
						   xtype: 'textfield',
						   maxLength: 100,
						   enforceMaxLength: true,
						   maxLengthText: 'Máximo de {0} caracteres.',
						   msgTarget: 'side',
						   labelWidth: 105,
						   labelAlign: 'top',
						   padding: '0 10 10 0'
					   },
					   items: [{
						   name: 'id',
						   fieldLabel: 'Código',
						   width: 110,
						   editable: false,
						   flex: 1,
						   bind : '{currentCadastro.id}'
						   //allowBlank: false
						   },

						   {
						   xtype: 'combobox',
						   name: 'pfPj',
						   reference: 'tipoPessoa',
						   //id: 'campo',
						   afterLabelTextTpl: Mynerp.util.Util.required,
						   fieldLabel: 'Tipo Pessoa',
						   labelWidth: 150,
						   flex: 2,
						   valueField: 'valor',
						   displayField: 'tipo',
						   typeAhead: true,
						   queryMode: 'local',
						   allowBlank: false,
						   forceSelection: true,
						   store: [['1','Fisica'],['2','Jurídica']],
						   bind : '{currentCadastro.pfPj}',
						   listeners: {
						        change: 'onChangeTipoPessoa'
						   }
						   },
						   {
						   fieldLabel: 'CPF',
						   flex: 2,
						   bind : '{currentCadastro.cpfCnpj}',
						   reference: 'cnpjFornecedor',
						   maskRe: /[0-9]/i,
						   maxLength: 18,
						   enableKeyEvents: true,
						   listeners: {
						        keypress: 'formataCpfCnpj'
						   }
						   //allowBlank: false
						   },
						   {
						   fieldLabel: 'Nome - Razão Social',
						   reference: 'nomeFornecedor',
						   maxLength: 60,
						   flex: 5,
						   allowBlank: false,
						   afterLabelTextTpl: Mynerp.util.Util.required,
						   bind : '{currentCadastro.nome}'
						   }	   
					   ]},
						{
						   xtype: 'container',
						   layout: 'hbox',
						   hidden: false, 
						   //combineErrors: true, //mostra todos os erros juntos
						   defaults: {
							   flex: 1,
							   xtype: 'textfield',
							   maxLength: 100,
						   	   enforceMaxLength: true,
						   	   maxLengthText: 'Máximo de {0} caracteres.',
							   msgTarget: 'side',
							   labelWidth: 105,
							   labelAlign: 'top',
							   padding: '0 10 10 0'
						   },
						   items: [{
							   xtype: 'combobox',
							   reference: 'sexoFornecedor',
							   forceSelection: true,
							   fieldLabel: 'Sexo',
							   labelWidth: 150,
							   flex: 2,
							   hidden: false,
							   valueField: 'valor',
							   displayField: 'tipo',
							   typeAhead: true,
							   queryMode: 'local',
							   forceSelection: true,
							   store: [['1','Masculino'],['2','Feminino']],
							   bind : '{currentCadastro.idsexo}'
							   },
							   {
							   xtype: 'datefield',
							   reference: 'nascimentoFornecedor',
							   fieldLabel: 'Data de Nascimento',
							   flex: 3,
							   hidden: false,
							   bind : '{currentCadastro.nascimento}'
							   },
							   {
							   fieldLabel: 'Nome Fantasia',
							   reference: 'fantasiaFornecedor',
							   maxLength: 60,
							   flex: 5,
							   hidden: true,
							   bind : '{currentCadastro.nomeFantasia}'
						   	   },
							   {
							   	xtype: 'container',
							   	name: 'containerVazio',
								layout: 'anchor',
								hidden: false,
								flex: 5
							   }		   
						   ]
						}
					]
				},
				// fieldset contato
				{
				xtype: 'fieldset',
				title: 'Informações de Contato',

				items: [
					   {
					   xtype: 'container',
					   layout: 'hbox',
					   combineErrors: true,
					   defaults: {
						   flex: 1,
						   xtype: 'textfield',
						   maxLength: 100,
						   enforceMaxLength: true,
						   maxLengthText: 'Máximo de {0} caracteres.',
						   msgTarget: 'side',
						   labelWidth: 105,
						   labelAlign: 'top',
						   padding: '0 10 10 0'
					   },
					   items: [{
						   fieldLabel: 'E-mail',
						   vtype: 'email',
						   maxLength: 50,
						   flex: 6,
						   bind : '{currentCadastro.email}'
						   //allowBlank: false
						   },	                	        	              	        	   
						   {
						   fieldLabel: 'ddd',
						   flex: 1,
						   maxLength: 3,
						   bind : '{currentCadastro.ddd}'
						   //allowBlank: false
						   },
						   {
						   fieldLabel: 'Telefone',
						   flex: 2,
						   maxLength: 10,
						   bind : '{currentCadastro.telefone}'
						   },
						   {
						   fieldLabel: 'ddd',
						   flex: 1,
						   maxLength: 3,
						   bind : '{currentCadastro.dddCelular}'
						   //allowBlank: false
						   },
						   {
						   fieldLabel: 'Celular',
						   flex: 2,
						   maxLength: 10,
						   bind : '{currentCadastro.celular}'
						   },
						   {
						   name: 'dddAdicional',
						   fieldLabel: 'ddd',
						   maxLength: 3,
						   flex: 1,
						   bind : '{currentCadastro.dddAdicional}'
						   //allowBlank: false
						   },
						   {
						   name: 'telefoneAdicional',
						   fieldLabel: 'Tel. Adicional',
						   maxLength: 10,
						   flex: 2,
						   bind : '{currentCadastro.telefoneAdicional}'
						   }
					   ]}
					]
				},

				{ //fieldset endereço
				xtype: 'fieldset',
				title: 'Endereço',

				items: [
					   {
					   xtype: 'container',
					   layout: 'hbox',
					   combineErrors: true,
					   defaults: {
						   flex: 1,
						   xtype: 'textfield',
						   maxLength: 100,
						   enforceMaxLength: true,
						   maxLengthText: 'Máximo de {0} caracteres.',
						   msgTarget: 'side',
						   labelWidth: 105,
						   labelAlign: 'top',
						   padding: '0 10 10 0'
					   },
					   items: [	                	        	              	        	   
						   {
						   xtype: 'combobox',
						   name: 'tipoLogradouro',
						   forceSelection: true,
						   fieldLabel: 'Tipo',
						   labelWidth: 150,
						   flex: 1,
						   valueField: 'tipo',
						   displayField: 'tipo',
						   typeAhead: true,
						   queryMode: 'local',
						   //allowBlank: false,
						   forceSelection: true,
						   store: ['Av.','Rua','Praça'],
						   bind : '{currentCadastro.tipoLogradouro}'
						   },
						   {
						   fieldLabel: 'Logradouro',
						   maxLength: 50,
						   flex: 5,
						   bind : '{currentCadastro.logradouro}'
						   //allowBlank: false
						   },
						   {
						   fieldLabel: 'Número',
						   maxLength: 10,
						   flex: 1,
						   bind : '{currentCadastro.numero}'
						   },
						   {
						   fieldLabel: 'Complemento',
						   maxLength: 50,
						   flex: 2,
						   bind : '{currentCadastro.complemento}'
						   }	   
					   ]},
						{
						   xtype: 'container',
						   layout: 'hbox',
						   hidden: false, 
						   combineErrors: true,
						   defaults: {
							   flex: 1,
							   xtype: 'textfield',
							   maxLength: 100,
						   	   enforceMaxLength: true,
						       maxLengthText: 'Máximo de {0} caracteres.',
							   msgTarget: 'side',
							   labelWidth: 105,
							   labelAlign: 'top',
							   padding: '0 10 10 0'
						   },
						   items: [
						   	   {
							   fieldLabel: 'Bairro',
							   maxLength: 50,
							   flex: 3,
							   bind : '{currentCadastro.bairro}'
						   	   },
						   	   {
							   fieldLabel: 'Cidade',
							   maxLength: 50,
							   flex: 3,
							   bind : '{currentCadastro.nomeCidade}'
						   	   },
						   	   {
							   xtype: 'combobox',
							   name: 'estado',
							   fieldLabel: 'UF',
							   labelWidth: 150,
							   flex: 1,
							   valueField: 'sigla',
							   displayField: 'sigla',
							   typeAhead: true,
							   queryMode: 'local',
							   forceSelection: true,
							   store: {
							   		type: 'UF'
							   		},
							   bind : '{currentCadastro.ufSigla}'
							   },  
							   {
							   fieldLabel: 'CEP',
							   maxLength: 10,
							   flex: 1,
							   bind : '{currentCadastro.cep}'
						   	   }	   
						   ]
						}
					]
				},

				{ //fieldset cobranca
				xtype: 'fieldset',
				title: 'Cobrança',

				items: [
					   {
					   xtype: 'container',
					   layout: 'hbox',
					   combineErrors: true,
					   defaults: {
						   flex: 1,
						   xtype: 'textfield',
						   msgTarget: 'side',
						   labelWidth: 105,
						   labelAlign: 'top',
						   padding: '0 10 10 0'
					   },
					   items: [	                	        	              	        	   
						   {
						   xtype: 'numberfield',
						   name: 'vencimento',
						   value: 1,
				           maxValue: 31,
				           minValue: 0,
						   fieldLabel: 'Dia de Vencimento',
						   labelWidth: 150,
						   flex: 2,
						   bind : '{currentCadastro.vencimento}'
						   },
						   {
						   	xtype: 'container',
						   	name: 'containerVazio',
							layout: 'anchor',
							hidden: false,
							flex: 8
						  }	   
					   ]}
					]
				}
			]

});