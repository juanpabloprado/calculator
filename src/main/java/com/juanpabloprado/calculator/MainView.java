package com.juanpabloprado.calculator;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Route("")
public class MainView extends VerticalLayout {

    public MainView(Calculator calculator) {

        VerticalLayout todosList = new VerticalLayout();
        TextField inputField = new TextField();
        Button addButton = new Button("=");
        addButton.addClickListener(click -> {
            String result = inputField.getValue() + " = ";

            // https://stackoverflow.com/a/13525053
            String[] strings = StringUtils.stripAll(inputField.getValue().split("(?<=[-+*/])|(?=[-+*/])"));
            List<String> tokens = List.of(strings);
            double r = calculator.processInput(tokens);

            Checkbox checkbox = new Checkbox(result + r);

            todosList.add(checkbox);
        });
        addButton.addClickShortcut(Key.ENTER);

        add(
                new H1("Calculator"),
                todosList,
                new HorizontalLayout(
                        inputField,
                        addButton
                )
        );
    }
}
