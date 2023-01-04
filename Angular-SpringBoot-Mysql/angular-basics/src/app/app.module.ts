import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DatabindingComponent } from './databinding/databinding.component';
import { PropertybindongComponent } from './propertybindong/propertybindong.component';
import { StylebindingComponent } from './stylebinding/stylebinding.component';
import { ClassbindingComponent } from './classbinding/classbinding.component';
import { DeptComponent } from './dept/dept.component';
import { MechComponent } from './dept/mech/mech.component';
import { EceComponent } from './dept/ece/ece.component';
import { AttriputebindingComponent } from './attriputebinding/attriputebinding.component';
import { FormsModule } from '@angular/forms';
import { EventbindingComponent } from './eventbinding/eventbinding.component';
import { DirectivesComponent } from './directives/directives.component';
@NgModule({
  declarations: [
    AppComponent,
    DatabindingComponent,
    PropertybindongComponent,
    StylebindingComponent,
    ClassbindingComponent,
    DeptComponent,
    MechComponent,
    EceComponent,
    AttriputebindingComponent,
    EventbindingComponent,
    DirectivesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
