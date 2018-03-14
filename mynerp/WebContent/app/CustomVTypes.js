
//coloquei par ficar igual exemplo do livro mas não entendi muito bem como funcionam estas expressões regulares.... devo comentar este trecho para fazer meus testes.

Ext.apply(Ext.form.field.VTypes, {
    //  vtype validation function
    customPass: function(val, field) {
        return /^((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{4,20})/.test(val);
    },
    // vtype Text property: The error text to display when the validation function returns false
    customPassText: 'Not a valid password.  Length must be at least 6 characters and maximum of 20. Password must contain one digit, one letter lowercase, one letter uppercase, one special symbol @#$% and between 4 and 20 characters.'
});

Ext.apply(Ext.form.field.VTypes, {
    //  vtype validation function
    customUser: function(val, field) {
        return /[0-9]/.test(val);
    },
    // vtype Text property: The error text to display when the validation function returns false
    customUserMask: /[0-9]/i, //este mask faz com que letras não sejam inseridas no campo. coloquei por conta própria
    customUserText: 'Usuário inválido.  Usuário deve ser um número de até quatro dígitos'
});