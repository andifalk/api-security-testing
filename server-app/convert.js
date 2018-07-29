var converter = require('oas-raml-converter');
var raml10ToOas20 = new converter.Converter(converter.Formats.RAML, converter.Formats.OAS20);

raml10ToOas20.convertFile('./build/ramldoc/api.raml').then(function(oas20) {
    console.log(oas20);
})
.catch(function(err) {
    console.error(err);
});
