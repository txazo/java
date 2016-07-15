package org.txazo.designpattern.behavior.mediator;

import org.junit.Test;

public class MediatorTest {

    @Test
    public void test() {
        RealEstateMediator mediator = new RealEstateMediator();
        Tenant tenant = new Tenant("租客-李先生", mediator);
        Landlord landlord = new Landlord("房东-张先生", mediator);
        tenant.intendRent("我想租两室一厅的房子");
        landlord.publishRent("出租三室一厅的房子");
    }

}
