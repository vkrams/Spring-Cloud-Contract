import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return a list of frauds"
    request{
        url "/fraud"
        method GET()
    }
    response {
        status 201
        body(["marcin","josh"])
    }
}
