import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalaryModalComponent } from './salary-modal.component';

describe('SalaryModalComponent', () => {
  let component: SalaryModalComponent;
  let fixture: ComponentFixture<SalaryModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SalaryModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SalaryModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
