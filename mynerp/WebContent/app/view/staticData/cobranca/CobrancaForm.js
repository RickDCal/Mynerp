Ext.define('Mynerp.view.staticData.cobranca.CobrancaForm', {
	extend: 'Ext.form.Panel',
	xtype: 'cobranca-form',

	//reference: 'clienteForm', // já foi passado na window, referente a este form que é item dela

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
				title: 'Cadastro',

				items: [
					   {
					   xtype: 'container',
					   layout: 'hbox',
					   defaultFocus: 'id', // acho que não funcionou
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
						   fieldLabel: 'Nome',
						   maxLength: 60,
						   flex: 4,
						   allowBlank: false,
						   afterLabelTextTpl: Mynerp.util.Util.required,
						   bind : '{currentCadastro.nome}'
						   }	   
					   ]},
						{
						   xtype: 'container',
						   layout: 'hbox',
						   defaultFocus: 'id', // acho que não funcionou
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
								xtype: 'checkboxfield',
								//fieldLabel: ' ',
								hideEmptyLabel: false,
								flex: 2,
								boxLabel: 'Conta Receber',
		                    	name: 'indCR',
		                    	//checked: true,
		                    	inputValue: 'pagar',
		                    	uncheckedValue: false,                    		
	                    		bind: '{currentCadastro.isReceber}'
							   },
							   {
								xtype: 'checkboxfield',
								//fieldLabel: ' ',
								hideEmptyLabel: false,
								flex: 2,
								boxLabel: 'Conta Pagar',
		                    	name: 'indCP',
		                    	//checked: true,
		                    	inputValue: 'receber',
		                    	uncheckedValue: false,                    		
	                    		bind: '{currentCadastro.isPagar}'
							   }
							      
					   ]},
					   {
						   xtype: 'container',
						   layout: 'hbox',
						   hidden: false, 
						   //combineErrors: true, //mostra todos os erros juntos
						   defaults: {							   
							   msgTarget: 'side',
							   labelAlign: 'top',
							   padding: '0 10 10 0'
						   },
						   items: [
						   		{
							   	xtype: 'container',
							   	name: 'containerVazio',
								layout: 'anchor',
								hidden: false,
								flex: 2
							    },	
						   	   {
							   xtype: 'datefield',
							   fieldLabel: 'Data de Desativação',
							   flex: 1,
							   labelWidth: 200,
							   hidden: false,
							   bind : '{currentCadastro.dataExclusao}'
							   }
							   		   	   
						   ]
						},
					]
				},
				
				
			]

});