'use strict';

const uuidv1 = require('uuid/v1');
// noinspection JSUnresolvedVariable
module.exports = {assignNewId};

function assignNewId(requestParams, context, eventEmitter, done) {
    const id = uuidv1();
    requestParams.body = requestParams.body
        .replace("@@id@@", id);
    return done();
}