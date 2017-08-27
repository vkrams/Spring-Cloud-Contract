import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should send a fraud message"
    label "triggerFraud"
    input{
        triggeredBy("triggerMessage()")
    }
    outputMessage
            {
                sentTo "frauds"
                body([surname: "hgdsjdhjsah"])
            }
}