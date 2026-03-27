import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearPaquetes } from './crear-paquetes';

describe('CrearPaquetes', () => {
  let component: CrearPaquetes;
  let fixture: ComponentFixture<CrearPaquetes>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CrearPaquetes]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CrearPaquetes);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
